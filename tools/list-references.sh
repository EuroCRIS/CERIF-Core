#!/bin/sh

# List references from *.md files to other *.md files, their frequencies and warn on those where the target is missing.
# To be run from the main directory of the CERIF-Core or a CERIF module repo.
#
# Parameters:
# - directories where the definitions could be also found.

(
# References from md files
find . "$@" -name \*.md | \
xargs grep -o -h -e '([-:./A-Z0-9a-z_]*\.md)' | \
sed -e 's/(\.\.*\//.\//' -e 's/)$//' \
    -e 's/(https:\/\/github.com\/[-A-Za-z0-9_.]*\/[-A-Za-z0-9_.]*\/blob\/[-A-Za-z0-9_.]*\//.\//' \
    -e '/\/XXX\.md/d' -e '/\/TEMPLATE_.*/d'
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
	-e '/^ *$/d' -e 's/^/.\/datatypes\//' -e 's/$/.md/'
) | \
sort | uniq -c | \
while read N F ; do
  X=0 
  for D in . "$@"
  do
    if [ -f "$D"/"$F" ]
      then X=1
    fi
  done
  if [ $X == '1' ]
    then echo $N "$F"
    else echo $N "$F" - MISSING!
  fi
done

find ./entities -name \*.md \
| xargs egrep -o -e '<a name="rel[^"]*' \
| sed -e 's/\.md:<a name="/.md#user-content-/' \
| sort >${TMPDIR}/CERIF-relationship-anchors-$$.txt
find ./entities -name \*.md \
| xargs egrep -H -o -e '\./entities/[A-Za-z0-9_]*\.md#user-content-rel__[^)]*' \
| sed -e 's/^\([^:]*\):\(.*\)$/\2 \1/' \
| sort \
| tee ${TMPDIR}/CERIF-relationship-targets-$$.txt \
| join -v1 - ${TMPDIR}/CERIF-relationship-anchors-$$.txt >${TMPDIR}/CERIF-relationship-mismatches-$$.txt
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
	sed -e 's/\(\.\/entities\/\)\([A-Za-z0-9_]*\)\(\.md\)/* [\2](\1\2\3)/' ${TMPDIR}/CERIF-entities-undescribed-$$.txt
fi

ls ./datatypes/*.md >${TMPDIR}/CERIF-datatypes-$$.txt
sed <README.md -e '1,/### Data Types/d' -e '/^## /,$d' \
| grep -oh -e '\(./datatypes/[A-Za-z0-9_]*\.md\)' \
| sort \
| join -v2 - ${TMPDIR}/CERIF-datatypes-$$.txt >${TMPDIR}/CERIF-datatypes-undescribed-$$.txt
if [ -s ${TMPDIR}/CERIF-datatypes-undescribed-$$.txt ] ; then
	echo
	echo 'Datatypes missing from the Listing / Datatypes section in the README.md file:'
	sed -e 's/\(\.\/datatypes\/\)\([A-Za-z0-9_]*\)\(\.md\)/* [\2](\1\2\3)/' ${TMPDIR}/CERIF-datatypes-undescribed-$$.txt
fi