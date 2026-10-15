#!/usr/bin/env bash

# List references from *.md files to other *.md files, their frequencies and warn on those where the target is missing.
# To be run from the main directory of the CERIF-Core or a CERIF module repo.
#
# Repositories are auto-discovered through their cerif2.ttl file: the current directory and
# each sibling ../CERIF-* directory (and the parameter directories below) that contains a
# cerif2.ttl with a doap:location GitHub URL form a map of GitHub repo names to local
# directories. References to other repositories (both as GitHub URLs and as relative paths
# like ../CERIF-Core/entities/X.md) are resolved through this map. A reference to a repo
# that is not checked out locally is reported as "not checked out!".
#
# Parameters:
# - directories where the definitions could be also found.

: ${TMPDIR:=/tmp}

REPO_MAP=${TMPDIR}/CERIF-repo-map-$$.txt
: >$REPO_MAP
for D in . ../CERIF-* "$@"
do
  [ -f "$D"/cerif2.ttl ] || continue
  NAME=$(sed -n 's/.*doap:location "https:\/\/github\.com\/[^/]*\/\([^/"]*\)\.git".*/\1/p' "$D"/cerif2.ttl)
  [ -n "$NAME" ] || continue
  DUPE=0
  while IFS=$'\t' read -r D2 N2
  do
    [ "$D2" = "$D" ] && DUPE=1
  done < $REPO_MAP
  if [ $DUPE -eq 0 ]
  then
    printf '%s\t%s\n' "$D" "$NAME" >>$REPO_MAP
  fi
done
REPO_DIRS=$(cut -f1 $REPO_MAP | sort -u)

repo_dir()
{
  awk -F'\t' -v n="$1" '$2==n {print $1; exit}' "$REPO_MAP"
}

(
# References from md files
find . "$@" -name \*.md | \
grep -v '/TEMPLATE_' | \
xargs grep -o -h -e '([-:./A-Z0-9a-z_]*\.md)' | \
sed -e 's/(\.\.*\//.\//' -e 's/)$//' \
    -e 's/(https:\/\/github\.com\/[^/]*\/\([^/]*\)\/blob\/[^/]*\/)/.\1\//' \
    -e '/TEMPLATE_[A-Z]*\.md/d' -e 's/^(//'
# Names of entities and datatypes in puml files
find . "$@" -name \*.puml | \
xargs grep -oh -e 'class "[A-Za-z0-9_]*"' -e 'datatype *( *"[A-Za-z0-9_]*" *)' | \
sed -e 's/^class "/.\/entities\//' -e 's/"$/.md/' \
    -e 's/^datatype( *"/.\/datatypes\//' -e 's/" *)$/.md/'
# Datatypes of attributes from class and datatype definitions in puml files
find . "$@" -name \*.puml | \
xargs sed -e '1,/{/d' -e '/}/,/{/d' -e '/}/,$d' \
	-e 's/[^^]*\^\^//' -e 's/[^:]*//' -e 's/^: *//' \
	-e '/<.*>/{ s/.*<//; s/>.*//; }' \
	-e '/^ *$/d' -e 's/^/.\/datatypes\//' -e 's/$/.md/' \
| grep -E '^\./datatypes/[A-Za-z0-9_]+\.md$'
) | \
grep -E '^(\./)?[A-Za-z0-9_-]+(/[A-Za-z0-9_.-]+)*\.md(#[A-Za-z0-9_-]+)?$' | \
sort | uniq -c | \
while read N F ; do
  FP=${F%%#*}
  X=0
  STATUS=
  for D in . "$@"
  do
    if [ -f "$D"/"$FP" ]
      then X=1
    fi
  done
  if [ $X -eq 0 ]
  then
    case $FP in
      ./*) P=${FP#./} ;;
      *) P=$FP ;;
    esac
    case $P in
      */*)
        C1=${P%%/*}
        DIR=$(repo_dir "$C1")
        if [ -n "$DIR" ]
        then
          [ -f "$DIR"/"${P#"$C1"/}" ] && X=1
        elif [ $C1 = entities ] || [ $C1 = datatypes ]
        then
          # entities and datatypes may be inherited from another (mapped) repo
          for R in $REPO_DIRS
          do
            [ -f "$R"/"$P" ] && X=1
          done
        fi
        case $C1 in
          CERIF-*) [ -z "$DIR" ] && STATUS=not\ checked\ out ;;
        esac
        ;;
      *)
        for D in . "$@"
        do
          [ -f "$D"/entities/"$FP" ] && X=1
          [ -f "$D"/datatypes/"$FP" ] && X=1
        done
        ;;
    esac
  fi
  if [ $X -eq 1 ]
    then echo $N "$F"
  elif [ -n "$STATUS" ]
    then echo $N "$F" - "$STATUS"!
  else
    echo $N "$F" - MISSING!
  fi
done

# Relationship anchors defined in the entities of all the mapped repos
: >${TMPDIR}/CERIF-relationship-anchors-$$.txt
for D in $REPO_DIRS
do
  [ -d "$D"/entities ] || continue
  find "$D"/entities -name \*.md | \
  xargs egrep -oH -e '<a name="rel[^"]*' | \
  sed -e 's/\.md:<a name="/.md#user-content-/' >>${TMPDIR}/CERIF-relationship-anchors-$$.txt
done
sort -o ${TMPDIR}/CERIF-relationship-anchors-$$.txt ${TMPDIR}/CERIF-relationship-anchors-$$.txt

# Relationship targets referenced from the Relationships sections of all the mapped repos
rm -rf ${TMPDIR}/CERIF-entities-$$ 2>/dev/null
mkdir -p ${TMPDIR}/CERIF-entities-$$
: >${TMPDIR}/CERIF-relationship-targets-$$.txt
for D in $REPO_DIRS
do
  [ -d "$D"/entities ] || continue
  I=${D//\//_}
  mkdir -p ${TMPDIR}/CERIF-entities-$$/$I/entities
  find "$D"/entities -name \*.md \
  | while read F ; do
      sed -e '1,/^## Relationships/d' -e '/^---/,$d' -e '/^##/,$d' <$F >${TMPDIR}/CERIF-entities-$$/$I/entities/${F##*/}
    done
  (
    cd ${TMPDIR}/CERIF-entities-$$/$I
    find ./entities -name \*.md \
    | xargs egrep -H -o -e '\./entities/[A-Za-z0-9_]*\.md#user-content-rel__[^)]*' \
                      -e 'https://github\.com/[^/]+/[A-Za-z0-9_-]+/blob/[^/]+/entities/[A-Za-z0-9_]*\.md#user-content-rel__[^)]*' \
    | sed -e 's/^\([^:]*\):\(.*\)$/\2 \1/' \
    | while read T S ; do
        case $T in
          https://github.com/*)
            R=${T#https://github.com/}
            R=${R#*/}
            NAME=${R%%/*}
            REST=${R#"$NAME"/blob/}
            REST=${REST#*/}
            DIR=$(repo_dir "$NAME")
            [ -n "$DIR" ] && echo "$DIR/$REST $D/${S#./}"
            ;;
          ./entities/*) echo "$D/${T#./} $D/${S#./}" ;;
          ../entities/*) echo "$D/${T#../} $D/${S#./}" ;;
        esac
      done >>${TMPDIR}/CERIF-relationship-targets-$$.txt
  )
done
sort -o ${TMPDIR}/CERIF-relationship-targets-$$.txt ${TMPDIR}/CERIF-relationship-targets-$$.txt

join -v1 ${TMPDIR}/CERIF-relationship-targets-$$.txt ${TMPDIR}/CERIF-relationship-anchors-$$.txt >${TMPDIR}/CERIF-relationship-mismatches-$$.txt
if [ -s ${TMPDIR}/CERIF-relationship-mismatches-$$.txt ] ; then
	echo
	echo 'Relationships with non-existent targets:'
	cat ${TMPDIR}/CERIF-relationship-mismatches-$$.txt
fi
join -v2 ${TMPDIR}/CERIF-relationship-targets-$$.txt ${TMPDIR}/CERIF-relationship-anchors-$$.txt >${TMPDIR}/CERIF-relationship-unused-anchors-$$.txt
if [ -s ${TMPDIR}/CERIF-relationship-unused-anchors-$$.txt ] ; then
	echo
	echo 'Unused relationship anchors:'
	cat ${TMPDIR}/CERIF-relationship-unused-anchors-$$.txt
fi

ls ./entities/*.md >${TMPDIR}/CERIF-entities-$$.txt
sed <README.md -e '1,/## Overview/d' -e '/^## /,$d' \
| grep -oh -e '\(./entities/[A-Za-z0-9_]*\.md\)' \
| sort \
| join -v2 - ${TMPDIR}/CERIF-entities-$$.txt >${TMPDIR}/CERIF-entities-undescribed-$$.txt
if [ -s ${TMPDIR}/CERIF-entities-undescribed-$$.txt ] ; then
	echo
	echo 'Entities missing from the Overview section in the README.md file:'
	cat ${TMPDIR}/CERIF-entities-undescribed-$$.txt
fi

ls ./entities/*.md >${TMPDIR}/CERIF-entities-$$.txt
sed <README.md -e '1,/### Entities/d' -e '/^### /,$d' \
| grep -oh -e '\(./entities/[A-Za-z0-9_]*\.md\)' \
| sort \
| join -v2 - ${TMPDIR}/CERIF-entities-$$.txt >${TMPDIR}/CERIF-entities-undescribed-$$.txt
if [ -s ${TMPDIR}/CERIF-entities-undescribed-$$.txt ] ; then
	echo
	echo 'Entities missing from the Listing / Entities section in the README.md file:'
	sed -e 's/\(\.\/entities\/\)\([A-Za-z0-9_]*\)\(\.md\)/[\2](\1\2\3)/' ${TMPDIR}/CERIF-entities-undescribed-$$.txt
fi
for F in diagrams/core.puml diagrams/module.puml
do
  if [ -f $F ]
  then
    grep 'class "' $F | sed -e 's/^.*class "/.\/entities\//' -e 's/".*/.md/' \
    | sort \
    | diff -U0 - ${TMPDIR}/CERIF-entities-$$.txt \
    | sed -e 1,2d -e '/^@@ /d' >${TMPDIR}/CERIF-entities-diff-diagram-$$.txt
    if [ -s ${TMPDIR}/CERIF-entities-diff-diagram-$$.txt ] ; then
     	echo
     	echo 'Entities from' $F 'vs entities defined in ./entities/:'
     	sed -e 's/\(\.\/entities\/\)\([A-Za-z0-9_]*\)\(\.md\)/\2/' ${TMPDIR}/CERIF-entities-diff-diagram-$$.txt
    fi
  fi
done

ls ./datatypes/*.md >${TMPDIR}/CERIF-datatypes-$$.txt 2>/dev/null
sed <README.md -e '1,/### Data Types/d' -e '/^## /,$d' \
| grep -oh -e '\(./datatypes/[A-Za-z0-9_]*\.md\)' \
| sort \
| join -v2 - ${TMPDIR}/CERIF-datatypes-$$.txt >${TMPDIR}/CERIF-datatypes-undescribed-$$.txt
if [ -s ${TMPDIR}/CERIF-datatypes-undescribed-$$.txt ] ; then
	echo
	echo 'Datatypes missing from the Listing / Datatypes section in the README.md file:'
	sed -e 's/\(\.\/datatypes\/\)\([A-Za-z0-9_]*\)\(\.md\)/[\2](\1\2\3)/' ${TMPDIR}/CERIF-datatypes-undescribed-$$.txt
fi
