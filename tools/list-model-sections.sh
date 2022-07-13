#!/bin/bash
# 
# Lists the model sections that are available and those that are referenced

find . -name \*.puml | \
xargs grep '!startsub' | \
sed -e 's/:[ 	]*!startsub \(.*\)/!\1 @exists@/' | \
sort -u >${TMPDIR}/CERIF-model-sections-$$.txt

find . -name \*.puml | \
xargs grep '!includesub' | \
sed -e 's/\([^ ]*\)\/\([^:]*\):.*!includesub \([^!]*\)!\(.*\)/\1\/\3!\4 \1\/\2/' | \
sort -u | \
join -t\  -a1 -a2 ${TMPDIR}/CERIF-model-sections-$$.txt - | \
sed -e '/ @exists@/!s/$/ - MISSING!/' -e 's/ @exists@//'
