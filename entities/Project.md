# Project

## Definition

A temporary endeavor undertaken to create a unique product, service or result.<br/>
Source: the Project Management Institute, https://www.pmi.org/about/learn-about-pmi/what-is-project-management

## Usage notes

In the research information domain, one typically tracks:
1. research projects, where the result is an addition to the body of knowledge of the mankind,
2. technology development projects, where the result is a particular technology or product,
3. innovation projects, where the result is an improvement of a product or process, and
4. projects that create or enhance infrastructure for research, technology development or innovation.

Depending on the scope one can also track finer levels of granularity: stages, work packages, sometimes even down to individual tasks. All such activities are also modelled using the Project entity and linked using the recursive link relationship.

The Project entity only captures details of the project scope and plan. Information about the resources needed to execute the project such as the funding (i.e., the grants received), the people and organisations involved, etc. is contained in separate entities ([Funding](../entities/Funding.md), [Person](../entities/Person.md), [OrgUnit](../entities/Organisation_Unit.md), respectively) and which are all linked to the Project through [Contributions to Project](../entities/Contribution_to_Project.md).

## Attributes

name : [Multilingual String](../datatypes/Multilingual_String.md)

acronym : [String](../datatypes/String.md)

## Relationships

(FIXME) part of (link to super Project structure, might be link between a task and working package, or working package and project)

(FIXME) funding source (link to fundings)

<a name="rel__has-contribution">has-contribution</a> / [has-target](../entities/Contribution_to_Project.md#user-content-rel__has-target) : A Project can have any number of [contributions](../entities/Contribution_to_Project.md) that help it happen.

---
## Matches


## References
