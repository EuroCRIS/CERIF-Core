#!/bin/bash
#
# Generate skeleton relationship markup.
# $1 - class1 readable name
# $2 - relationship role1-2 readable name
# $3 - class2 readable name
# $4 - relationship role2-1 readable name
#
# Example:
# ./tools/new-relationship.sh "Activity" "be covered by" "Funding" "cover"
#
echo "$1|${1// /_}|$2|${2// /-}|$3|${3// /_}|$4|${4// /-}" | \
sed -e 's/\([^|]*\)|\([^|]*\)|\([^|]*\)|\([^|]*\)|\([^|]*\)|\([^|]*\)|\([^|]*\)|\(.*\)/--\
\2.md:\
<a name="rel__\4">A \1 can *[\3](..\/entities\/\6.md#user-content-rel__\8)* any number of \5, instances of [\5](..\/entities\/\6.md).<\/a>\
--\
\6.md:\
<a name="rel__\8">A \5 can *[\7](..\/entities\/\2.md#user-content-rel__\4)* any number of \1, instances of [\1](..\/entities\/\2.md).<\/a>\
--/'