# CERIF Core
CERIF (=Common European Research Information Format) covers the domain of research information with a focus on the administrative and organizational aspects.
It aims to provide machine-processable representation of research information and cover situations where research information crosses borders between organizations (or between organizations and people).
This typically covers information interchanges between the Current Research Information Systems of these organizations, but other cases exist as well.
The information interchanged is not necessarily intended to be public.

## Overview
[Persons](./entities/Person.md), [Groups](./entities/Group.md) and [Organisation Units](./entities/Organisation_Unit.md) are [Agents](./entities/Agent.md).
Agents can perform [Activities](./entities/Activity.md).

Persons can act on their own, in affiliation to or on behalf of an Organisation Unit or a Group (optionally in their Position).
In the latter cases, an [Affiliation Statement](./entities/Affiliation_Statement.md) describes the connection. Multiple Affiliation Statements are possible.

A Group can have any number of Agents as members (so we track [Memberships](./entities/Membership.md), one type of Activity).

Membership is a specific type of [Involvement](./entities/Involvement.md) in a [Group or Organisation Unit](./entities/Group_or_Organisation_Unit.md).
Other types are [Employment](./entities/Employment.md) and [Education](./entities/Education.md).
Involvements can reference a [Position](./datatypes/Position.md) that describes the capacities of the involved Agent.

Research is typically carried out in [Projects](./entities/Project.md). 
Projects need [Resources](./entities/Resource.md), such as [Funding](./entities/Funding.md).
Bringing Resources of different kinds is a way how Agents can make [Contributions to Projects](./entities/Contribution_to_Project.md).

Any Resource can be [requested](./entities/Resource_Request.md), 
[offered](./entities/Resource_Offer.md), 
[provided](./entities/Contribution_Statement.md) or 
actually [used](./entities/Resource_Usage_Statement.md).

Projects typically make use of [Infrastructure](./entities/Infrastructure.md).
Agents can make [Contributions to Infrastructure](./entities/Contribution_to_Infrastructure.md).
[Repositories](./entities/Repository.md) are one subtype of Infrastructures.

Scientific and other [Events](./entities/Event.md) support communication, including scholarly communication. 
Agents can make different [Contributions to Events](./entities/Contribution_to_Event.md). An Event can have any number of [Event Identifiers](./entities/Event_Identifier.md) assigned. Events can be organized within [Event Series](./entities/Event_Series.md)

Information and knowledge are typically stored in [Documents](./entities/Document.md).
Agents can be actors in [Contributions to Documents](./entities/Contribution_to_Document.md).
Documents can be [published](./entities/Document_Publication.md) in one or several [Publication Channels](./entities/Publication_Channel.md).
The form of a document can be further specified as [Text](./entities/Textual_Contents.md), [Video](./entities/Video_Contents.md), [Audio](./entities/Audio_Contents.md) or [Tangible](./entities/Tangible.md).
Digital objects can be marked as [Accessible on the Web](./entities/Accessible_on_the_Web.md) or even [Downloadable](./entities/Downloadable.md).

Agents prepare and submit [Applications](./entities/Application.md), for instance [Funding Applications](./entities/Funding_Application.md).
This can occur in response to a [Call for Applications](./entities/Call_for_Applications.md), for instance a [Call for Funding Applications](./entities/Call_for_Funding_Applications.md), or outside of any such call, perhaps based on an implicit invitation to do so.
Once submitted, an application, will go through some kind of evaluation process and in the end there will be a [Decision](./entities/Decision.md).
On the way to it there may be [Evaluation Outcomes](./entities/Evaluation_Outcome.md).
Agents can make [Contributions to Evaluation Outcomes](./entities/Contribution_to_Evaluation_Outcome.md), most notably by doing the [Application_Review](./entities/Application_Review.md).

All the contributions mentioned above are subclasses of [Contribution](./entities/Contribution.md).
A Contribution is another type of Activity.
Contributions can be detailed with any number of [Contribution Statements](./entities/Contribution_Statement.md).
These can in turn be further refined with [Resource Usage Statements](./entities/Resource_Usage_Statement.md) which represent the fact of a Resource being used for the Activity.
A Contribution can be acknowledged by an [Acknowledgment Statement](./entities/Acknowledgement_Statement.md).

[Expertise or Skills](./entities/Expertise_and_Skills.md) can be [posessed](./entities/Expertise_and_Skills_Possession.md) by Agents.
When joined with availability and willingness in [Expertise and Time and Effort](./entities/Expertise_and_Time_and_Effort.md), it forms yet another subclass of Resource.
Expertise and Skills can also be [conditions](./entities/Expertise_and_Skills_Condition.md) for a [Resource Offer](./entities/Resource_Offer.md),
a specific subtype of a general [Condition](./entities/Condition.md).

[Prizes](./entities/Prize.md) are conferred on Agents in [Prize Awards](./entities/Prize_Award.md).
Agents also make [Contributions to Prizes](./entities/Contribution_to_Prize.md) or [Contributions to Prize Awards](./entities/Contribution_to_Prize_Award.md).

An Agent can have any number of [Agent Identifiers](./entities/Agent_Identifier.md) assigned,
for instance the [ORCID Identifier](./entities/ORCID_Identifier.md), [ROR Identifier](./entities/ROR_Identifier.md), [ISNI Identifier](./entities/ISNI_Identifier.md),
[FundRef Identifier](./entities/FundRef_Identifier.md), [Wikidata Agent Identifier](./entities/Wikidata_Agent_Identifier.md), 
or [Local Agent Identifier](./entities/Local_Agent_Identifier.md). 

A Resource can have any number of [Resource Identifiers](./entities/Resource_Identifier.md) assigned,
for instance the [DOI Identifier](./entities/DOI_Identifier.md), [Wikidata Resource Identifier](./entities/Wikidata_Resource_Identifier.md), or [Local Resource Identifier](./entities/Local_Resource_Identifier.md).

## Listings

### Entities
* [Agent](./entities/Agent.md)
  * [Person](./entities/Person.md)
  * [Organisation Unit](./entities/Organisation_Unit.md), [Group](./entities/Group.md) (or [Group_or_Organisation_Unit](./entities/Group_or_Organisation_Unit.md) to represent the union of these two)
* [Activity](./entities/Activity.md)
  * [Contribution](./entities/Contribution.md)
    * [Contribution to Document](./entities/Contribution_to_Document.md)
    * [Contribution to Project](./entities/Contribution_to_Project.md)
    * [Contribution to Event](./entities/Contribution_to_Event.md)
    * [Contribution to Evaluation Outcome](./entities/Contribution_to_Evaluation_Outcome.md)
      * [Application Review](./entities/Application_Review.md)
    * [Contribution_to_Infrastructure](./entities/Contribution_to_Infrastructure.md)
    * [Contribution_to_Prize](./entities/Contribution_to_Prize.md)
    * [Contribution_to_Prize_Award](./entities/Contribution_to_Prize_Award.md)
  * [Involvement](./entities/Involvement.md)
    * [Membership](./entities/Membership.md)
    * [Employment](./entities/Employment.md)
    * [Education](./entities/Education.md)
* [Affiliation](./entities/Affiliation_Statement.md)
* [Acknowledgement_Statement](./entities/Acknowledgement_Statement.md)
* [Contribution Statement](./entities/Contribution_Statement.md)
* [Project](./entities/Project.md)
* [Event](./entities/Event.md)
* [Event Series](./entities/Event_Series.md)
* [Publication Channel](./entities/Publication_Channel.md)
* [Resource](./entities/Resource.md)
  * [Funding](./entities/Funding.md)
  * [Expertise and Time and Effort](./entities/Expertise_and_Time_and_Effort.md)
  * [Infrastructure](./entities/Infrastructure.md)
    * [Repository](./entities/Repository.md)
  * [Document](./entities/Document.md)
* [Resource Offer](./entities/Resource_Offer.md)
  * [Call for Applications](./entities/Call_for_Applications.md)
    * [Call for Funding Applications](./entities/Call_for_Funding_Applications.md)
* [Resource Request](./entities/Resource_Request.md)
  * [Application](./entities/Application.md)
    * [Funding Application](./entities/Funding_Application.md)
* [Resource Usage Statement](./entities/Resource_Usage_Statement.md)
* [Condition](./entities/Condition.md)
  * [Expertise and Skills Condition](./entities/Expertise_and_Skills_Condition.md)
* [Evaluation Outcome](./entities/Evaluation_Outcome.md)
* [Decision](./entities/Decision.md) 	
* [Prize](./entities/Prize.md)
* [Prize Award](./entities/Prize_Award.md)
* [Expertise and Skills](./entities/Expertise_and_Skills.md)
* [Expertise and Skills Possession](./entities/Expertise_and_Skills_Possession.md)
* [Agent Identifier](./entities/Agent_Identifier.md)
  * [ORCID Identifier](./entities/ORCID_Identifier.md)
  * [ROR Identifier](./entities/ROR_Identifier.md)
  * [ISNI Identifier](./entities/ISNI_Identifier.md)
  * [FundRef Identifier](./entities/FundRef_Identifier.md)
  * [Wikidata Agent Identifier](./entities/Wikidata_Agent_Identifier.md)
  * [Local Agent Identifier](./entities/Local_Agent_Identifier.md)
* [Resource Identifier](./entities/Resource_Identifier.md)
  * [DOI identifier](./entities/DOI_Identifier.md)
  * [Wikidata Resource Identifier](./entities/Wikidata_Resource_Identifier.md)
  * [Local Resource Identifier](./entities/Local_Resource_Identifier.md)
* [Event Identifier](./entities/Event_Identifier.md)
* [Textual_Contents](./entities/Textual_Contents.md)
* [Audio_Contents](./entities/Audio_Contents.md)
* [Video_Contents](./entities/Video_Contents.md)
* [Downloadable](./entities/Downloadable.md)
* [Accessible on the Web](./entities/Accessible_on_the_Web.md)
* [Tangible](./entities/Tangible.md)
* [Document Publication](./entities/Document_Publication.md)

### Data Types
* [String](./datatypes/String.md)
* [Date](./datatypes/Date.md), [Date Range](./datatypes/Date_Range.md)
* [Duration](./datatypes/Duration.md)
* [Time](./datatypes/Time.md)
* [Boolean](./datatypes/Boolean.md)
* [Decimal](./datatypes/Decimal.md)
* [Multilingual String](./datatypes/Multilingual_String.md)
  * [HTML Multilingual String](./datatypes/HTML_Multilingual_String.md) 
* [ORCID iD Type](./datatypes/ORCID_iD.md)
* [ROR ID Type](./datatypes/ROR_ID.md)
* [URI Type](./datatypes/URI.md)
* [DOI ID Type](./datatypes/DOI_ID.md)
* [Fundref ID Type](./datatypes/FundRef_ID.md)
* [ISNI ID Type](./datatypes/ISNI_ID.md)
* [Wikidata ID Type](./datatypes/Wikidata_ID.md)
* [Local Agent ID Type](./datatypes/Local_Agent_ID.md)
* [Local Resource ID Type](./datatypes/Local_Resource_ID.md)
* [Person Name](./datatypes/Person_Name.md)
* [Physical Address](./datatypes/Physical_Address.md)
  * [Simple Physical Address](./datatypes/Simple_Physical_Address.md)
  * [Structured Physical Address](./datatypes/Structured_Physical_Address.md)
* [Contact Information](./datatypes/Contact_Information.md)
  * [Postal Address](./datatypes/Postal_Address.md)
  * [Visiting Address](./datatypes/Visiting_Address.md)
  * [Electronic Address](./datatypes/Electronic_Address.md)
    * [Email Address](./datatypes/Email_Address.md) 
    * [Website Address](./datatypes/Website_Address.md)
    * [Phone Number](./datatypes/Phone_Number.md)
* [Position](./datatypes/Position.md)
* [Language](./datatypes/Language.md), [Country](./datatypes/Country.md), [Script](./datatypes/Script.md)
* [Language Tag](./datatypes/Language_Tag.md)
* [Monetary Amount](./datatypes/Monetary_Amount.md)
* Codes for languages: [ISO 639-1 Alpha2 Code](./datatypes/ISO_639_1_Alpha2_Code.md), [ISO 639-2B Alpha3 Code](./datatypes/ISO_639_2B_Alpha3_Code.md), [ISO 639-2T Alpha3 Code](./datatypes/ISO_639_2T_Alpha3_Code.md)
* Codes for countries: [ISO 3166 Alpha2 Code](./datatypes/ISO_3166_Alpha2_Code.md)
* Codes for currencies: [ISO 4217 Alpha Code](./datatypes/ISO_4217_Alpha_Code.md)
* Codes for scripts: [ISO 15924 Alpha4 Code](./datatypes/ISO_15924_Alpha4_Code.md)
* [Contents Location](./datatypes/Contents_Location.md)
  * [Page Based Contents Location](./datatypes/Page_Based_Contents_Location.md) 
  * [Time Based Contents Location](./datatypes/Time_Based_Contents_Location.md)

## Illustrative Diagram
![The CERIF Core diagram](./diagrams/core.svg)

## Usage
The Core is seldom used on its own, one almost always needs to add one or several additional modules.
We include the following example:
* [Person](./examples/Person_Example1.md)

## Status
This is the experimental Core of CERIF, 
the result of the [CERIF Refactoring Pilot](https://www.eurocris.org/cerif-refactoring-project-introduction) project 
started in 2021 by [euroCRIS](https://www.eurocris.org/).
The scope, structure or any other aspect can change.
It has not been approved as the official standard yet.

## Credits
The development is carried out by [Jan Dvořák](https://orcid.org/0000-0001-8985-152X) and [Dragan Ivanović](https://orcid.org/0000-0002-9942-5521). We very much appreciate the feedback and corrections by José Francisco Salm jr.

## Development
We follow some [guidelines](./guidelines#cerif-guidelines) and use some [tools](./tools#cerif-core-tools).
