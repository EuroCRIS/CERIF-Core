# Contribution Statement

## Definition
An element of a [Contribution](../entities/Contribution.md). All such elements taken together describe the Contribution.

## Usage Notes
An example of a structured contribution statement:

> Author 1 did the [writing of the original draft (a CRediT term)](https://credit.niso.org/contributor-roles/writing-original-draft/).

An example of a free text contribution statement:

> Author 3 contributed much to Section 1.

Another example of a structured contribution statement:

> Agency XYZ provided Funding to project PQR.

## Attributes
statement: [Multilingual String](../datatypes/Multilingual_String.md)

## Relationships

<a name="rel__references">references</a> / [has-contribution-statement](../entities/Contribution.md#user-content-rel__has-contribution-statement) : A Contribution Statement references the [Contribution](../entities/Contribution.md) to which it relates.

<a name="rel__provides">provides</a> / [is-provided-by](../entities/Resource.md#user-content-rel__is-provided-by) : A Contribution Statement can optionally provide any number of [Resources](../entities/Resource.md). These resources can be funding, expertise, equipment, time or space.

<a name="rel__is-detailed-in">is-detailed-in</a> / [details](../entities/Resource_Usage_Statement.md#user-content-rel__details) : A Contribution Statement can be detailed in any number of [Resource Usage Statements](../entities/Resource_Usage_Statement.md).
