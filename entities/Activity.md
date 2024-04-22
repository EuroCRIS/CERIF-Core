# Activity

## Definition
Anything an Agent does.

## Usage notes
This records any research-related activity of an [Agent](../entities/Agent.md) (i.e., [Person](../entities/Person.md), [Organization](../entities/Organisation_Unit.md) or a [Group](../entities/Group.md)). 
Activities represent [Contributions](../entities/Contribution.md) to different things (to [Projects](../entities/Contribution_to_Project.md), [Events](../entities/Contribution_to_Event.md), [Documents](../entities/Contribution_to_Document.md), ...) 
or [Involvements](../entities/Involvement.md) in [Groups or Organisation Units](../entities/Group_or_Organisation_Unit.md).

## Attributes
date range : [Date Range](../datatypes/Date_Range.md)

## Relationships

<a name="rel__has-actor">has-actor</a> / [is-actor-in](../entities/Agent.md#user-content-rel__is-actor-in) : An Activity typically has the actor: an instance of [Agent](../entities/Agent.md). For some subclasses of Activity, the Agent may not be known or disclosed (e.g. for blind reviews). Also if the actor uses a pseudonym, their identity (and sometimes event the type) are unknown, in which case just a display name is available.

<a name="rel__has-affiliation-statement">has-affiliation-statement</a> / [is-used-in](../entities/Affiliation_Statement.md#user-content-rel__is-used-in) : An Activity can have any number of [Affiliation Statements](../entities/Affiliation_Statement.md).

<a name="rel__is-evidenced-by">is-evidenced-by</a> / [proves](../entities/Document.md#user-content-rel__proves) : An Activity can be evidenced by any number of [Documents](../entities/Document.md).

<a name="rel__is-covered-by">is-covered-by</a> / [covers](../entities/Funding.md#user-content-rel__covers) : An Activity can be covered by any number of [Fundings](../entities/Funding.md).


## Illustrative Diagram
![The Activity diagram](../diagrams/activity.svg)

---
## Matches

## References
