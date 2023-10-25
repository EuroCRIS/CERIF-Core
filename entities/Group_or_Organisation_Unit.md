# Group or Organisation Unit

## Definition
A common supertype for [Group](../entities/Group.md) and [Organisation Unit](../entities/Organisation_Unit.md).

## Specialization of
[Agent](../entities/Agent.md)

## Attributes
name: [Multilingual String](../datatypes/Multilingual_String.md)

acronym: [String](../datatypes/String.md)

description: [Multilingual String](../datatypes/Multilingual_String.md)

## Relationships
<a name="rel__has-involvement">A Group or Organisation Unit can *[have any number of involvements](../entities/Involvement.md#user-content-rel__in)*, instances of [Involvement](../entities/Involvement.md), which involve [Agents](../entities/Agent.md).</a>
