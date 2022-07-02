#!/bin/bash
#
# Generate skeleton entity.
# $1 - entity readable name
#
sed <./guidelines/TEMPLATE_ENTITY.md >./entities/${1// /_}.md \
	-e '1c\
# '"$1"