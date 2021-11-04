#!/bin/sh

# Generate the svg forms of the diagrams from the source puml files
#
# Parameters:
# - list of paths where to look for a 'diagrams' subdirectory
#   (the current directory is always included)

for D in . "$@"
do
	java -jar ../CERIF-Core/tools/plantuml.jar -charset UTF-8 -v -tsvg "$D/diagrams/**.puml" || \
	exit 1
done
