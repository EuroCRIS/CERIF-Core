# CERIF Core

This is the Core of CERIF (=Common European Research Information Format), the result of 
the [CERIF Refactoring Pilot](https://www.eurocris.org/cerif-refactoring-project-introduction) project 
started in 2021 by [euroCRIS](https://www.eurocris.org/).

## Status

(2021-06-24) This is experimental. The scope, structure or any other aspect can change.

## Scope

[Agents](./entities/Agent.md) can perform [Activities](./entities/Activity.md). 
[Persons](./entities/Person.md), [Groups](./entities/Group.md) and [Organisation Units](./entities/Organisation_Unit.md) are Agents. 
A Group can have any number of Agents as members (so we track [Memberships](./entities/Membership.md), one type of an Activity).
A Person can act on their own, in affiliation to or on behalf of an Organisation Unit or a Group. In the latter cases, [Affiliation Statements](./entities/Affiliation_Statement.md) describe the connection.

Research is typically carried out in [Projects](./entities/Project.md). 
Projects need [Resources](./entities/Resource.md), such as [Funding](./entities/Funding.md).
Bringing Resources of different kinds is a way how Agents can make [Contributions to Projects](./entities/Contribution_to_Project.md).

Scientific and other [Events](./entities/Event.md) support communication, including scholarly communication. 
Agents can make different [Contributions to Events](./entities/Contribution_to_Event.md).

Information and knowledge are typically stored in [Documents](./entities/Document.md), for instance [Textual Documents](./entities/Textual_Document.md).
Agents can make different [Contributions to Documents](./entities/Contribution_to_Document.md), most notably that of [Authorship](./entities/Authorship.md).
Accessibility of Documents is a pressing issue, so it can be expressed in [Textual Document Accessibility Specifications](./entities/Textual_Document_Accessibility_Specification.md).

Agents prepare and submit [Applications](./entities/Application.md), for instance [Funding Applications](./entities/Funding_Application.md).
This can occur in response to a [Call for Applications](./entities/Call_for_Applications.md), for instance a [Call for Funding Applications](./entities/Call_for_Funding_Applications.md), or outside of any such call, perhaps based on an implicit invitation to do so.
Once submitted, an application, will go through some kind of evaluation process and in the end there will be a [Decision](./entities/Decision.md).
On the way to it there may be [Evaluation Outcomes](./entities/Evaluation_Outcome.md).
Agents can make [Contributions to Evaluation Outcomes](./entities/Contribution_to_Evaluation_Outcome.md), most notably by doing the [Application_Review](./entities/Application_Review.md).

All the contributions mentioned above are subclasses of [Contribution](./entities/Contribution.md).
A Contribution is another type of Activity.

## Listings

### Entities

* [Agent](./entities/Agent.md)
  * [Person](./entities/Person.md)
  * [Organisation Unit](./entities/Organisation_Unit.md), [Group](./entities/Group.md) (or [Organisation Unit with Members](./entities/Organisation_Unit_with_Members.md) to represent the intersection of these two)
* [Activity](./entities/Activity.md)
  * [Contribution](./entities/Contribution.md)
    * [Contribution to Document](./entities/Contribution_to_Document.md)    
      * [Authorship](./entities/Authorship.md)
    * [Contribution to Project](./entities/Contribution_to_Project.md)
    * [Contribution to Event](./entities/Contribution_to_Event.md)
  * [Membership](./entities/Membership.md)
* [Document](./entities/Document.md)
  * [Textual Document](./entities/Textual_Document.md)
* [Affiliation Statement](./entities/Affiliation_Statement.md)
* [Contribution Statement](./entities/Contribution_Statement.md)
* [Textual Document Accessibility Specification](./entities/Textual_Document_Accessibility_Specification.md)
* [Project](./entities/Project.md)
* [Event](./entities/Event.md)
* [Resource](./entities/Resource.md)
  * [Funding](./entities/Funding.md)
* [Call for Applications](./entities/Call_for_Applications.md)
  * [Call for Funding Applications](./entities/Call_for_Funding_Applications.md)
* [Application](./entities/Application.md)
  * [Funding Application](./entities/Funding_Application.md) 
* [Evaluation Outcome](./entities/Evaluation_Outcome.md)
* [Decision](./entities/Decision.md) 	

### Data Types

* [String](./datatypes/String.md)
* [Date](./datatypes/Date.md)
* [Boolean](./datatypes/Boolean.md)
* [Multilingual String](./datatypes/Multilingual_String.md)
* [ORCID iD Type](./datatypes/ORCID_iD.md)
* [ROR ID Type](./datatypes/ROR_ID.md)
* [URI](./datatypes/URI.md)
* [Person Name](./datatypes/Person_Name.md)
* [Postal Address](./datatypes/Postal_Address.md)
* [Language](./datatypes/Language.md), [Country](./datatypes/Country.md), [Script](./datatypes/Script.md)
* [Language Tag](./datatypes/Language_Tag.md)
* [Monetary Amount](./datatypes/Monetary_Amount.md)
* Codes for languages: [ISO 639-1 Alpha2 Code](./datatypes/ISO_639_1_Alpha2_Code.md), [ISO 639-2B Alpha3 Code](./datatypes/ISO_639_2B_Alpha3_Code.md), [ISO 639-2T Alpha3 Code](./datatypes/ISO_639_2T_Alpha3_Code.md)
* Codes for countries: [ISO 3166 Alpha2 Code](./datatypes/ISO_3166_Alpha2_Code.md)
* Codes for currencies: [ISO 4217 Alpha Code](./datatypes/ISO_4217_Alpha_Code.md)
* Codes for scripts: [ISO 15924 Alpha4 Code](./datatypes/ISO_15924_Alpha4_Code.md)
* ...

## Illustrative Diagram

![The CERIF Core diagram](./diagrams/core.svg)

## Usage

The Core is seldom used on its own, you almost always need to add one or several additional modules.
Anyway, the Core includes the following examples:
* [Person](./examples/Person_Example1.md)
* ...

## Development

### Guidelines

Guidelines for the development of this and related projects are put in the [guidelines](./guidelines/) subdirectory.
Currently we specify how [entities](./guidelines/DESCRIBING_ENTITIES.md) 
and [datatypes](./guidelines/DESCRIBING_DATATYPES.md) should be described 
and how [diagrams](./guidelines/MAKING_DIAGRAMS.md) should be made.

### Tools

The [tools](./tools/) directory contains some useful tools:

* `./tools/list-references.sh [DIR]...` checks the integrity of the links between the entities and datatypes in this project together with projects specified by the list of directories one passes as parameters. It writes the targets of references together with their frequency. If a target is missing, the word `MISSING` is appended.

* `./tools/generate-diagrams.sh [DIR]...` generates the UML diagrams in this project together with projects specified by the list of directories one passes as parameters. You need Java 8 or later installed for this to run. We use [Plant UML](https://plantuml.com/) (in `./tools/plantuml.jar`) for this.

* `./tools/new-relationship-uuid.sh` generates a skeleton for describing relationship. The requirement is to have uuidgen installed to run the script. 

### Continuous Integration

We use a [Github action](./.github/workflows/plantuml.yml) to re-generate the SVG diagrams.
