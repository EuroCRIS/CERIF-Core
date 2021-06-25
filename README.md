# CERIF Core

This is the Core of CERIF (=Common European Research Information Format), the result of 
the [CERIF Refactoring Pilot](https://www.eurocris.org/cerif-refactoring-project-introduction) project 
started in 2021 by [euroCRIS](https://www.eurocris.org/).

## Status

(2021-06-24) This is experimental.

## Scope

The Core consists of the following entities:
* [Agent](./entities/Agent.md)
  * [Person](./entities/Person.md), [Organisation Unit](./entities/Organisation_Unit.md)
* Activity
* [Document](./entities/Document.md)
  * [Textual Document](./entities/Textual_Document.md)
* [Contributorship](./entities/Contributorship.md)
  * [Authorship](./entities/Authorship.md)
* [Affiliation Statement](./entities/Affiliation_Statement.md)
* [Contribution Statement](./entities/Contribution_Statement.md)
  * [Free Text Contribution Statement](./entities/Free_Text_Contribution_Statement.md)
* [Textual Document Accessibility Specification](./entities/Textual_Document_Accessibility_Specification.md)
* [Language Tag](./entities/Language_Tag.md), [Language](./entities/Language.md), [Country](./entities/Country.md), [Currency](./entities/Script.md), [Script](./entities/Script.md)
* ...

And the following data types:
* [String](./datatypes/String.md)
* [Date](./datatypes/Date.md)
* [Boolean](./datatypes/Boolean.md)
* [Multilingual String](./datatypes/Multilingual_String.md)
* [ORCID iD Type](./datatypes/ORCID_iD.md)
* [ROR ID Type](./datatypes/ROR_ID.md)
* [URI](./datatypes/URI.md)
* [Person Name](./datatypes/Person_Name.md)
* [Postal Address](./datatypes/Postal_Address.md)
* [ISO 3166 Alpha2 Code](./datatypes/ISO_3166_Alpha2_Code.md)
* [ISO 15924 Alpha4 Code](./datatypes/ISO_15924_Alpha4_Code.md)
* ...

## Usage

The Core is seldom used on its own, you almost always need to add one or several additional modules.

## Development Tools

The [tools](./tools/) directory contains useful tools for checking the integrity of this repository.
