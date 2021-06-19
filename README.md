# CERIF Core

This is the Core of CERIF (=Common European Research Information Format), the result of 
the [CERIF Refactoring Pilot](https://www.eurocris.org/cerif-refactoring-project-introduction) project 
started in 2021 by [euroCRIS](https://www.eurocris.org/).

## Scope

The Core consists of the following entities:
* Agent: [Person](./entities/Person.md), OrgUnit
* Activity
* Document, TextualDocument
* Contributorship, Authorship
* AffiliationStatement
* LanguageTag, Language, Country, Currency, Script
* ...

And the following data types:
* MultilingualString
* [ORCID iD Type](./datatypes/ORCID_iD_Type.md)
* ...

## Usage

The Core is seldom used on its own, you almost always need to add one or several additional modules.
