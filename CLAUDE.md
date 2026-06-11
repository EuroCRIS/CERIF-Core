# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is about

CERIF Core is a **data model specification** for the Common European Research Information Format (CERIF), developed by [euroCRIS](https://www.eurocris.org/). The primary artifacts are Markdown files in `entities/` and `datatypes/` that define the model; PlantUML diagrams in `diagrams/`; and a Java tool that generates RDF/OWL serializations from those Markdown files. It is still experimental and not yet an approved standard.

## Commands

All scripts are run from the **repository root**.

| Task | Command |
|---|---|
| Check link/reference integrity | `./tools/list-references.sh` |
| Generate SVG diagrams from `.puml` files | `./tools/generate-diagrams.sh` |
| Build the OWL tool and generate RDF | `./tools/compile-owl-tool-and-run-it.sh` |
| Generate RDF only (skip Maven build) | `./tools/compile-owl-tool-and-run-it.sh --no-compile` |
| Build the OWL Java tool alone | `cd tools/owl && mvn clean package` |
| Create a new entity skeleton | `./tools/new-entity.sh "Entity Name" ["Parent Entity Name"]` |
| Create a new relationship (inserts into both entity files) | `./tools/new-relationship.sh "Class1" "role1to2" "Class2" "role2to1"` |

**Requires Java 8+** for diagram generation and the OWL tool. Diagrams use `tools/plantuml.jar`.

CI (`.github/workflows/refresh-generated-files.yml`) runs `generate-diagrams.sh` and `compile-owl-tool-and-run-it.sh` on every push and commits the results automatically. Deploys to cerif2.eu only on `main`.

## Architecture

### Markdown-as-model

`entities/*.md` and `datatypes/*.md` are the authoritative source for the model. The Java OWL tool (`tools/owl/`) parses these files to generate `serializations/RDF/` — do not edit files under `serializations/` by hand; they are regenerated on every push.

### Entity/Datatype file structure

Entity files (see `guidelines/DESCRIBING_ENTITIES.md` and template `guidelines/TEMPLATE_ENTITY.md`) follow this section order:
1. Definition, Usage notes, Specialization of, Attributes, Relationships, Constraints, Illustrative Diagram
2. Separator `---`, then: Matches, References

Datatype files (see `guidelines/DESCRIBING_DATATYPES.md` and templates `TEMPLATE_DATATYPE_COMPLEX.md` / `TEMPLATE_DATATYPE_SIMPLE.md`) follow similar structure with components or restrictions depending on whether it is complex or simple.

Headings are omitted when a section is empty.

**Inheritance rule**: inherited attributes and relationships are **not re-listed** in subclasses; instead, the subclass references the superclass section (e.g. `[Document relationships](../entities/Document.md#relationships)`).

### Naming conventions

- **Entities and datatypes**: capitalized words with spaces in prose (e.g. `Affiliation Statement`); underscores in filenames, URIs, and PlantUML (e.g. `Affiliation_Statement`).
- **Attributes and relationship ends**: lowercase words with spaces in prose (e.g. `web site URL`); camelCase in UML and interchange formats (e.g. `webSiteURL`).

### Relationships

Relationships are bidirectional and documented in **both** entity files. Each end carries an `<a name="rel__role-name">` anchor so cross-links can target them precisely. `new-relationship.sh` inserts the skeleton into both files at once.

### Diagrams

PlantUML sources are in `diagrams/*.puml`; rendered SVGs alongside them. `core.puml` contains the full model; other `.puml` files show focused sub-diagrams. The `!startsub` / `!endsub` blocks in `core.puml` allow partial includes by module repos that depend on Core.

### Module extensibility

CERIF Core is designed to be extended by separate module repos. Scripts accept extra directory arguments (e.g. `./tools/list-references.sh ../CERIF-Module-X`) to check cross-repo link integrity or generate diagrams that span multiple modules.
