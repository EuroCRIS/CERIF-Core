# CERIF Core

This is the Core of CERIF (=Common European Research Information Format), the result of 
the [CERIF Refactoring Pilot](https://www.eurocris.org/cerif-refactoring-project-introduction) project 
started in 2021 by [euroCRIS](https://www.eurocris.org/).

## Status

(2021-06-24) This is experimental.

## Scope

The Core consists of the following entities:
* [Agent](./entities/Agent.md)
  * [Person](./entities/Person.md), [Organisation Unit](./entities/OrgUnit.md)
* Activity
* [Document](./entities/Document.md)
  * [Textual Document](./entities/TextualDocument.md)
* [Contributorship](./entities/Contributorship.md)
  * [Authorship](./entities/Authorship.md)
* [Affiliation Statement](./entities/AffiliationStatement.md)
* [Contribution Statement](./entities/ContributionStatement.md)
  * [Free Text Contribution Statement](./entities/FreeTextContributionStatement.md)
* [Textual Document Accessibility Specification](./entities/TextualDocumentAccessibilitySpecification.md)
* Language Tag, Language, [Country](./entities/Country.md), Currency, Script
* ...

And the following data types:
* [String](./datatypes/String.md)
* [Date](./datatypes/Date.md)
* [Boolean](./datatypes/Boolean.md)
* [Multilingual String](./datatypes/MultilingualString.md)
* [ORCID iD Type](./datatypes/ORCID_iD_Type.md)
* [ROR ID Type](./datatypes/ROR_ID_Type.md)
* [URI](./datatypes/URI.md)
* [Person Name](./datatypes/PersonName.md)
* [PostalAddress](./datatypes/PostalAddress.md)
* [ISO3166 Alpha Code Type](../datatypes/ISO3166AlphaCode.md)
* [ISO3166 Numeric Code Type](../datatypes/ISO3166NumericCode.md)
* [EU Country Code Type](../datatypes/EUCountryCode.md)
* ...

## Usage

The Core is seldom used on its own, you almost always need to add one or several additional modules.
