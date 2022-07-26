#!/bin/bash
#
# Generate skeleton entity.
# $1 - entity readable name
# $2 - parent entity readable name (optional)
#
Q=${TMPDIR}/CERIF-entity-$$.sed
cat >$Q <<EOC
1c\\
# $1
EOC
if [ ! -z "$2" ] ; then
	P="[$2](../entities/${2// /_}.md)"
	cat >>$Q <<EOC
/^## Specialization of/{
n;
c\\
$P

}
/^## Attributes/a\\
Besides those of $P:\\

/^## Relationships/a\\
Besides those of $P:\\

EOC
else
	cat >>$Q <<EOC
/^## Specialization of/{N;N;d;}
EOC
fi
sed <$( dirname $0 )/../guidelines/TEMPLATE_ENTITY.md >./entities/${1// /_}.md -f $Q