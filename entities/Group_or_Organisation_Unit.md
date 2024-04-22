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

<a name="rel__has-involvement">has-involvement</a> / [involves-actor-in](../entities/Involvement.md#user-content-rel__involves-actor-in) : A Group or Organisation Unit can have any number of [Involvements](../entities/Involvement.md), which involve [Agents](../entities/Agent.md) in it.
