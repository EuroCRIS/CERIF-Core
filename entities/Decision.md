# Decision

## Definition

The conclusions from considering a [Resource Request](../entities/Resource_Request.md).

## Usage notes

When some resource is requested, a Decision should be made. That decision might be documented by a [Document](../entities/Document.md) and can be based on some kind of process which results in an [Evaluation Outcome](../entities/Evaluation_Outcome.md).

## Attributes

date : [Date](../datatypes/Date.md)

## Relationships

<a name="rel__is-expressed-in">is-expressed-in</a> / [presents-decision](../entities/Document.md#user-content-rel__presents-decision) : A Decision can be expressed in a [Document](../entities/Document.md).

<a name="rel__based-on">based-on</a> / [provides-basis-for](../entities/Evaluation_Outcome.md#user-content-rel__provides-basis-for) : A Decision can be based on any number of [Evaluation Outcomes](../entities/Evaluation_Outcome.md).

<a name="rel__is-a-decision-on">is-a-decision-on</a> / [has-decision](../entities/Resource_Request.md#user-content-rel__has-decision) : A Resource Request can have a [Decision](../entities/Decision.md) about accepting or refusing request made by addressee.

<a name="rel__made-by">made-by</a> / [made](../entities/Agent.md#user-content-rel__made) : A Decision about accepting or refusing request can be made by any number of [Agents](../entities/Agent.md).
