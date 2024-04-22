# Expertise and Time and Effort

## Definition
**Expertise**: A high level of knowledge or skill.<sup>[1](#fn1)</sup>

**Time**: An amount of time that you have available to do something.<sup>[2](#fn2)</sup>

**Effort**: Physical or mental activity needed to achieve something.<sup>[3](#fn3)</sup>

## Usage notes
This class represents the expertise and time and effort of an [Agent](../entities/Agent.md) as a [Resource](../entities/Resource.md),
which can be [offered](../entities/Resource_Offer.md), [requested](../entities/Resource_Request.md), [provided](../entities/Contribution_Statement.md) or actually [used](../entities/Resource_Usage_Statement.md).

## Specialization of
[Resource](../entities/Resource.md)

## Attributes
Besides those of [Resource](../entities/Resource.md):

<a name="amount-of-time-in-person-hours">amount of time in person hours</a> : [Decimal](../datatypes/Decimal.md)

## Relationships
Besides those of [Resource](../entities/Resource.md):

<a name="rel__by-agent">by-agent</a> / [is-agent-in2](../entities/Agent.md#user-content-rel__is-agent-in2) : An Expertise and Time and Effort references the [Agent](../entities/Agent.md) whose expertise, time and effort it would be.

---
## References
<a name="fn1">\[1\]</a> Source: The Cambridge Dictionary of English. https://dictionary.cambridge.org/dictionary/english/expertise

<a name="fn2">\[2\]</a> Source: The Cambridge Dictionary of English. https://dictionary.cambridge.org/dictionary/english/time

<a name="fn3">\[3\]</a> Source: The Cambridge Dictionary of English. https://dictionary.cambridge.org/dictionary/english/effort
