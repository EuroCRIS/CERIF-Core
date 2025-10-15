# Contribution

## Definition
An activity that helps achieve a goal.

## Usage notes
This represents any contribution an [Agent](../entities/Agent.md) makes to a [Project](../entities/Contribution_to_Project.md), [Event](../entities/Contribution_to_Event.md), or a [Document](../entities/Contribution_to_Document.md).
The particular ways of contributing are represented by the linked [Contribution Statements](../entities/Contribution_Statement.md).

## Specialization of
[Activity](../entities/Activity.md)

## Attributes
Those of [Activity](../entities/Activity.md) plus:

contribution sorting key : [Decimal](../datatypes/Decimal.md) – An optional numeric sorting key: for one linked object it establishes the sequence of contributions of a specific kind. This is to be used where the order is conventionally important, such as authors or editors of a Document.

## Relationships
Beside those inherited from [Activity](../entities/Activity.md#relationships):

<a name="rel__has-contribution-statement">has-contribution-statement</a> / [references](../entities/Contribution_Statement.md#user-content-rel__references) : A Contribution can have any number of [Contribution Statements](../entities/Contribution_Statement.md).

<a name="rel__is-testified-by">is-testified-by</a> / [testify](../entities/Acknowledgement_Statement.md#user-content-rel__testify) : A Contribution can be testified by any number of [Acknowledgement Statements](../entities/Acknowledgement_Statement.md).

---
## Matches

## References
