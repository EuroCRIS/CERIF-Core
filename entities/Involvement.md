# Involvement

## Definition
The act or process of taking part in something,<sup>[1](#fn1)</sup> namely in a [Group or Organisation Unit](../entities/Group_or_Organisation_Unit.md).

## Usage notes
This entity represents any kind of relationship between the *[actor](../entities/Agent.md#user-content-rel__activity)* and a [Group or Organisation Unit](../entities/Group_or_Organisation_Unit.md).

[Membership](../entities/Membership.md), [Employment](../entities/Involvement.md) and [Education](../entities/Education.md) are examples of Involvement.

Involvements may form a hierarchic structure which may in part reflect a hierarchy of Groups or Organisation Units.

## Specialization of
[Activity](../entities/Activity.md)

## Attributes

Those of [Activity](../entities/Activity.md).

description : [Multilingual String](../datatypes/Multilingual_String.md)

position    : [Position](../datatypes/Position.md)

## Relationships

<a name="rel__involves-actor-in">involves-actor-in</a> / [has-involvement](../entities/Group_or_Organisation_Unit.md#user-content-rel__has-involvement) : A Involvement specifies that the *actor* Agent is involved in a [Group or Organisation Units](../entities/Group_or_Organisation_Unit.md).

<a name="rel__is-part-of">is-part-of</a> / [has-part](../entities/Involvement.md#user-content-rel__has-part) : An Involvement can be part of any number of other [Involvements](../entities/Involvement.md).

<a name="rel__has-part">has-part</a> / [is-part-of](../entities/Involvement.md#user-content-rel__is-part-of) : An Involvement can have any number of part [Involvements](../entities/Involvement.md).

## Illustrative Diagram
A UML diagram showing this entity in its context if one is available.

---
## References
<a name="fn1">\[1\]</a> Source: The Cambridge Dictionary of English. https://dictionary.cambridge.org/dictionary/english/involvement
