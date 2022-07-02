# CERIF Core Tools

## Useful scripts
This subdirectory contains some useful tools (to be run from the main directory of either CERIF-Core or a module):

* `./tools/list-references.sh [DIR]...` checks the integrity of the links between the entities and datatypes in this project together with projects specified by the list of directories one passes as parameters. It writes the targets of references together with their frequency. If a target is missing, the word `MISSING` is appended.

* `./tools/generate-diagrams.sh [DIR]...` generates the UML diagrams in this project together with projects specified by the list of directories one passes as parameters. You need Java 8 or later installed for this to run. We use [Plant UML](https://plantuml.com/) (in `./tools/plantuml.jar`) for this.

* `./tools/new-relationship.sh` generates a skeleton for describing relationship. Supply the classes and the phrases as arguments, e.g.
```
./tools/new-relationship.sh Activity "be covered by" Funding "cover"
```

## Continuous Integration
On every push to this repository a [Github action](../.github/workflows/plantuml.yml) re-generates the SVG [diagram files](../diagrams/).
If any of the diagrams changes, a new commit is added to the respective branch.
