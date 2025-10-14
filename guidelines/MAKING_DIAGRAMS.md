# Making UML Diagrams

We use the [PlantUML](https://plantuml.com/) to provide UML diagrams.
The source files of the diagrams (`*.puml`) in the PlantUML notation, together with their SVG renderings (`*.svg`), 
are stored in the [diagrams](../diagrams/) directory. 
There is a [Github action](../.github/workflows/plantuml.yml) to re-build the SVG renderings on every push.

## Workflows

When making your modifications, you'll probably be best served by your IDE with a PlantUML plug-in installed.
There is one available for [IntelliJ Idea](https://plugins.jetbrains.com/plugin/7017-plantuml-integration), 
for [Eclipse](https://hallvard.github.io/plantuml/) and for [many other tools](https://plantuml.com/sitemap-plugins) too.

A second best option is using the [interactive PlantUML workbench](http://www.plantuml.com/plantuml/uml/) to shape your diagram.

The third best option is to make your changes in the source files and run the `./tools/generate-diagrams.sh` script. 
Your working directory should be the main directory either of a module or of the Core.
You can pass relative paths to other main directories of modules where you want to generate the SVG files in addition to the active module.

Once you push any commits, there will be a [Github action](../.github/workflows/plantuml.yml) to re-build the SVG renderings.

## PlantUML for CERIF Essential Syntax

FIXME

