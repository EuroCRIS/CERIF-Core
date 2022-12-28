# Agent

## Definition
Anything that has the ability to perform activities.

## Usage notes
Currently either [Person](../entities/Person.md), 
[Organisation Unit](../entities/Organisation_Unit.md),
or [Group](../entities/Group.md),
for future extension.

## Attributes
description: [Multilingual String](../datatypes/Multilingual_String.md)

keywords: List<[Multilingual String](../datatypes/Multilingual_String.md)>

ISNI ID : [ISNI ID Type](../datatypes/ISNI_ID.md)

Wikidata ID : [Wikidata ID Type](../datatypes/Wikidata_ID.md)

lifetime : [Date Range](../datatypes/Date_Range.md)

## Relationships
<a name="rel__activity">An Agent can *[be the actor](../entities/Activity.md#user-content-rel__actor)* of any number of [Activities](../entities/Activity.md).</a>

<a name="rel__receives">An Agent can *[receive](../entities/Prize_Award.md#user-content-rel__recipient)* any number of [Prize Awards](../entities/Prize_Award.md).</a>

<a name="rel__in-expertise-and-skills-possession">An Agent can *[be the agent in](../entities/Expertise_and_Skills_Possession.md#user-content-rel__by-agent)* any number of [Expertise and Skills Possessions](../entities/Expertise_and_Skills_Possession.md).</a>

<a name="rel__in-expertise-and-time-and-effort">An Agent can *[be the agent in](../entities/Expertise_and_Time_and_Effort.md#user-content-rel__agent)* any number of [Expertise and Time and Effort](../entities/Expertise_and_Time_and_Effort.md).</a>

## Illustrative Diagram
![The Agent diagram](../diagrams/agent.svg)

---
## Matches
1. Close match of [FOAF Agent](http://xmlns.com/foaf/spec/#term_Agent) 
