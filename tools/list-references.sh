#!/bin/sh

# List references from *.md files to other *.md files, their frequencies and warn on those where the target is missing.
# To be run from the main directory of the CERIF-Core repo.

find . -name \*.md | \
xargs grep -o -h -e '([./A-Za-z_]*\.md)' | \
sed -e 's/(\.\.*\//.\//' -e 's/)$//' | \
sort | uniq -c | \
while read N F ; do 
  if [ -f $F ]
    then echo $N $F
    else echo $N $F - MISSING!
  fi
done
