#!/bin/bash
#
# Insert skeleton relationship markup into the entity files, at the end of the respective Relationships sections
# $1 - class1 readable name
# $2 - relationship role1-2 readable name
# $3 - class2 readable name
# $4 - relationship role2-1 readable name
#
# Example:
# ./tools/new-relationship.sh "Activity" "be covered by" "Funding" "cover"
#
X1="${1// /_}"
X2="${2// /-}"
X3="${3// /_}"
X4="${4// /-}"
L1=$( cat ./entities/$X1.md | awk 'BEGIN { AFTER_REFS=0 } /^## Relationships/ { AFTER_REFS=1 ; next } ( AFTER_REFS && /^(## |---)/ ) { print (NR-1) "i" ; AFTER_REFS=0 }' )
C1=${L1:-\$a}
L3=$( cat ./entities/$X3.md | awk 'BEGIN { AFTER_REFS=0 } /^## Relationships/ { AFTER_REFS=1 ; next } ( AFTER_REFS && /^(## |---)/ ) { print (NR-1) "i" ; AFTER_REFS=0 }' )
C3=${L3:-\$a}
sed -i~ -e "$C1\\
\\
<a name=\"rel__$X2\">$X2<\/a> \/ [$X4](..\\/entities\\/$X3.md#user-content-rel__$X4) : A $1 can $2 any number of [${3}s](..\\/entities\\/$X3.md)." ./entities/$X1.md
sed -i~ -e "$C3\\
\\
<a name=\"rel__$X4\">$X4<\/a> \/ [$X2](..\\/entities\\/$X1.md#user-content-rel__$X2) : A $3 can $4 any number of [${1}s](..\\/entities\\/$X1.md)." ./entities/$X3.md
