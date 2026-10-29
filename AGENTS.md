# AGENTS.md

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
| List the diagram model sections (available vs. referenced via `!includesub`) | `./tools/list-model-sections.sh` |
| Deploy generated RDF to cerif2.eu manually over ssh | `./tools/publish-RDF-on-main.sh` (does nothing off `main`; CI also deploys on every push) |

**Requires Java 8+** for diagram generation and the OWL tool. Diagrams use `tools/plantuml.jar`.

CI (`.github/workflows/refresh-generated-files.yml`) runs `generate-diagrams.sh` and `compile-owl-tool-and-run-it.sh` on every push and commits the results automatically. Deploys to cerif2.eu only on `main`.

## Architecture

### Markdown-as-model

`entities/*.md` and `datatypes/*.md` are the authoritative source for the model. The Java OWL tool (`tools/owl/`) parses these files to generate `serializations/RDF/` — do not edit files under `serializations/` by hand; they are regenerated on every push.

### Adding and editing model elements

There is no script for creating datatypes: create the file by hand from `guidelines/TEMPLATE_DATATYPE_SIMPLE.md` or `guidelines/TEMPLATE_DATATYPE_COMPLEX.md` (entities and relationships are handled by `new-entity.sh` / `new-relationship.sh` above).

After adding or removing entities, datatypes, or relationships, run `generate-diagrams.sh` and `compile-owl-tool-and-run-it.sh` immediately, and commit the regenerated artifacts together with the Markdown changes, along with the updated `README.md` rows (the narrative entity lists and the entity/datatype index). Textual edits to an existing datatype (e.g. refining a restrictions regular expression) do not change the serializations and require no regeneration — the serializations carry the declarations and structure, not the datatype prose.

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

CERIF Core is designed to be extended by separate module repos. Scripts accept extra directory arguments (e.g. `./tools/list-references.sh ../CERIF-Module-X`) to check cross-repo link integrity or generate diagrams that span multiple modules. In this workspace the module repos are checked out side-by-side (e.g. `../CERIF-InstrumentsModule`). When a module references a new Core element (e.g. in its mapping tables), commit the Core change first, then update and commit the module.

### Regular-expression restrictions

Identifier datatypes carry regular-expression restrictions. Lessons from devising them (see `Handle_ID.md` vs. the simpler `DOI_ID.md`):

- Ground the syntax in the relevant specification's ABNF, but constrain to the *practical* syntax where the formal grammar is looser than actual practice (e.g. RFC 3651 allows arbitrary UTF-8 handle naming authorities, yet the Global Handle Registry only issues numeric prefixes).
- Iterate the pattern against positive and negative test cases until all pass — in particular all spellings the datatype must reject (URI and URL forms of the identifier, scheme-less hostname forms) and malformed values (empty prefix/suffix, superfluous dots, etc.). Verify with a throwaway script (e.g. `python3` plus `re`) instead of by inspection, and do not commit the script.
- Anchor the pattern (`^...$`) so it cannot merely match inside superset strings under a partial-matching implementation — essential whenever a restriction excludes URI/URL spellings of an identifier.
- Document the resulting design decisions and any deviation from the spec in the restriction bullet, with the specification cited in the footnotes.

## Git conventions

Git commit messages are generally structured as follows:
1. Subject line, follows the "If applied, this commit will ..." pattern
2. Descriptive body containing more details about the commit; optional, and usually omitted for commits proposed by an AI agent, as the prompts listed below carry the context
3. In case the commit is proposed by an AI agent, a Co-Authored-By line mentioning the agent and the organization on whose behalf the AI agent runs, e.g. in commit ec3d668f
4. In case the commit is proposed by an AI agent, the important prompts are listed as lines starting with "Prompt:", each on its own line separated by blank lines
