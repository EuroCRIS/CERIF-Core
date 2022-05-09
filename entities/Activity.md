# Activity

## Definition
Any academic activity of a [Person](../entities/Person.md), [Organization](../entities/Organisation_Unit.md) or a [Group](../entities/Group.md). For instance, the activity might represent [contribution](../entities/Contribution.md) to a [Project](../entities/ContributionshipToProject.md), [Event](../entities/ContributionshipToEvent.md), or a [Document](Contributorship_to_Document.md), or a [membership in an Association](../entities/Membership.md).

## Usage notes

## Attributes


## Relationships

An Activity typically has the *[actor](../entities/Agent.md)*: an instance of [Agent](../entities/Agent.md).</a> For some subclasses of Activity, the Agent may not be known (e.g. for blind reviews). Also if the actor uses a pseudonym, their identity (and sometimes event the type) are unknown, in which case just the display name is available.

An Activity has any number of *[affiliations](../entities/Affiliation_Statement.md)*: instances of [Affiliation Statement](../entities/Affiliation_Statement.md).</a>

[comment]: # (TODO: Proof of the activity)


## Illustrative Diagram

![The Activity diagram](../diagrams/activity.svg)

---
## Matches

## References
