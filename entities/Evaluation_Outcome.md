# Decision

## Definition

The conclusions of an evaluation process.

## Usage notes

When some resource is requested, an evaluation process can be run. Outcome from this process can be basis for a final [Decision](../entities/Decision.md).

## Attributes

conslusions : [Multilingual String](../datatypes/Multilingual_String.md)

## Relationships

<a name="rel__has-contribution">has-contribution</a> / [has-target](../entities/Contribution_to_Evaluation_Outcome.md#user-content-rel__has-target) : An Evaluation Outcome can have any number of [contributions](../entities/Contribution_to_Evaluation_Outcome.md) that helped it arise.

<a name="rel__provides-basis-for">provides-basis-for</a> / [based-on](../entities/Decision.md#user-content-rel__based-on) : Conclusions from evaluation process can be a basis for final [Decision](../entities/Decision.md).

<a name="rel__is-an-evaluation-outcome-of">is-an-evaluation-outcome-of</a> / [has-evaluation-outcome](../entities/Resource_Request.md#user-content-rel__has-evaluation-outcome) : An Evaluation outcome might be a result of the [Resource Request](../entities/Resource_Request.md) evaluation process.

---
## Matches

## References
