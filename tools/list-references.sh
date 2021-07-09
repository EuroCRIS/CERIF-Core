#!/bin/sh

# List references from *.md files to other *.md files, their frequencies and warn on those where the target is missing.
# To be run from the main directory of the CERIF-Core or a CERIF module repo.
#
# Parameters:
# - directories where the definitions could be also found.

find . "$@" -name \*.md | \
xargs grep -o -h -e '([-:./A-Z0-9a-z_]*\.md)' | \
sed -e 's/(\.\.*\//.\//' -e 's/)$//' \
    -e 's/(https:\/\/github.com\/[-A-Za-z0-9_.]*\/[-A-Za-z0-9_.]*\/blob\/[-A-Za-z0-9_.]*\//.\//' | \
sort | uniq -c | \
while read N F ; do
  X=0 
  for D in . "$@"
  do
    if [ -f $D/$F ]
      then X=1
    fi
  done
  if [ $X == '1' ]
    then echo $N $F
    else echo $N $F - MISSING!
  fi
done
