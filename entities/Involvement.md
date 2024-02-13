# Involvement

## Definition
The act or process of taking part in something,<sup>[1](#fn1)</sup> namely in a [Group or Organisation Unit](../entities/Group_or_Organisation_Unit.md).

## Usage notes
This entity represents any kind of relationship between the *[actor](../entities/Agent.md#user-content-rel__activity)* and a [Group or Organisation Unit](../entities/Group_or_Organisation_Unit.md).

[Membership](../entities/Membership.md), [Employment](../entities/Involvement.md) and [Education](../entities/Education.md) are examples of Involvement.

Involvements may form a hierarchic structure which may in part reflect a hierarchy of Groups or Organisation Units.

## Specialization of
[Activity](../entities/Activity.md)

## Relationships
<a name="rel__in">An Involvement specifies that the Agent is *[involved in](../entities/Group_or_Organisation_Unit.md#user-content-rel__has-involvement)* a [Group or Organisation Unit](../entities/Group_or_Organisation_Unit.md).</a>

<a name="rel__have-parent">An Involvement can have a *[parent](../entities/Involvement.md#user-content-rel__have-child)* [Involvement](../entities/Involvement.md).</a>
Inversely, <a name="rel__have-child">an Involvement can have any number of *[child](../entities/Involvement.md#user-content-rel__have-parent)* [Involvements](../entities/Involvement.md).</a>

## Illustrative Diagram
A UML diagram showing this entity in its context if one is available.

---
## References
<a name="fn1">\[1\]</a> Source: The Cambridge Dictionary of English. https://dictionary.cambridge.org/dictionary/english/involvement
