# Contribution

## Definition
An activity that helps achieve a goal.

## Usage notes
This represents any contribution an [Agent](../entities/Agent.md) makes to a [Project](../entities/Contribution_to_Project.md), [Event](../entities/Contribution_to_Event.md), or a [Document](../entities/Contribution_to_Document.md).
The particular ways of contributing are represented by the linked [Contribution Statements](../entities/Contribution_Statement.md).

## Specialization of
[Activity](../entities/Activity.md)

## Attributes
Just those of [Activity](../entities/Activity.md).

## Relationships
Beside those inherited from [Activity](../entities/Activity.md#relationships):

<a name="rel__has-contribution-statement">A Contribution has any number of *[contribution statements](../entities/Contribution_Statement.md#user-content-rel__contribution)*: instances of [Contribution Statement](../entities/Contribution_Statement.md).</a>

<a name="rel__is-testified-by">is-testified-by</a> — [testify](../entities/Acknowledgement_Statement.md#user-content-rel__testify) : A Contribution can be testified by any number of [Acknowledgement Statements](../entities/Acknowledgement_Statement.md).

---
## Matches

## References
