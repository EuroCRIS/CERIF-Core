# Agent

## Definition
Anything that has the ability to perform activities.

## Usage notes
Currently either [Person](../entities/Person.md), 
[Organisation Unit](../entities/Organisation_Unit.md),
or [Group](../entities/Group.md),
for future extension.

## Attributes

keywords: List<[Multilingual String](../datatypes/Multilingual_String.md)>

lifetime : [Date Range](../datatypes/Date_Range.md)

contacts : List<[Contact_Information](../datatypes/Contact_Information.md)>

## Relationships
<a name="rel__activity">An Agent can *[be the actor](../entities/Activity.md#user-content-rel__actor)* of any number of [Activities](../entities/Activity.md).</a>

<a name="rel__solicits">An Agent can *[invite](../entities/Call_for_Applications.md#user-content-rel__inviter)* any number of [Calls for Applications](../entities/Call_for_Applications.md).</a>

<a name="rel__receives">An Agent can *[receive](../entities/Prize_Award.md#user-content-rel__recipient)* any number of [Prize Awards](../entities/Prize_Award.md).</a>

<a name="rel__in-expertise-and-skills-possession">An Agent can *[be the agent in](../entities/Expertise_and_Skills_Possession.md#user-content-rel__by-agent)* any number of [Expertise and Skills Possessions](../entities/Expertise_and_Skills_Possession.md).</a>

<a name="rel__in-expertise-and-time-and-effort">An Agent can *[be the agent in](../entities/Expertise_and_Time_and_Effort.md#user-content-rel__agent)* any number of [Expertise and Time and Effort](../entities/Expertise_and_Time_and_Effort.md).</a>

<a name="rel__has-identifier">An Agent can *[have](../entities/Agent_Identifier.md#user-content-rel__is-assigned-to)* any number of [Agent_Identifiers](../entities/Agent_Identifier.md).</a>

## Illustrative Diagram
![The Agent diagram](../diagrams/agent.svg)

---
## Matches
1. Close match of [FOAF Agent](http://xmlns.com/foaf/spec/#term_Agent) 
