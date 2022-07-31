# Funding

## Definition
Money provided by an organization or government for a particular purpose.<sup>[1](#fn1)</sup>

## Usage notes
Fundings typically make up hierarchies, where the breakdown follows various criteria such as funding source, cost type, or fiscal terms.

Like any other [Resource](../entities/Resource.md), Funding can can be [offered](../entities/Resource_Offer.md), [requested](../entities/Resource_Request.md), [provided](../entities/Contribution_Statement.md) or actually [used](../entities/Resource_Usage_Statement.md).

## Specialization of
[Resource](../entities/Resource.md)

## Attributes
Those of [Resource](../entities/Resource.md) plus:

amount : [Monetary Amount](../datatypes/Monetary_Amount.md)

## Relationships
Those of [Resource](../entities/Resource.md) plus:

<a name="rel__cover">A Funding can *[cover](../entities/Activity.md#user-content-rel__be-covered-by)* any number of [Activities](../entities/Activity.md).</a>

<a name="rel__is-part-of">A Funding can *[be part of](../entities/Funding.md#user-content-rel__has-part)* any number of [Fundings](../entities/Funding.md).</a>

<a name="rel__has-part">A Funding can have any number of [Funding](../entities/Funding.md) as *[parts](../entities/Funding.md#user-content-rel__is-part-of)*.</a>

---
## References
<a name="fn1">\[1\]</a> Source: The Oxford Learner's Dictionary of Academic English, https://www.oxfordlearnersdictionaries.com/definition/academic/funding
