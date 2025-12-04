# Funding Application

## Definition
An application submitted in the form and manner described by a Funding agency, the purpose of which is to provide information to a Commission for project selection.<sup>[1](#fn1)</sup>

## Specialization of
[Application](../entities/Application.md)

## Attributes
Those of [Application](../entities/Application.md#attributes)

## Relationships
Those inherited from [Application](../entities/Application.md#relationships). The [Funding](../entities/Funding.md) that is requested in this Funding Application is referenced through the derived *of* association from [Resource Request](../entities/Resource_Request.md).

## Constraints 
The derived *in-response-to* association from [Application](../entities/Application.md) can only reference a [Call for Funding Applications](../entities/Call_for_Funding_Applications.md).

The derived *of* association from [Resource Request](../entities/Resource_Request.md) can only reference a single [Resource](../entities/Resource.md) which must be a [Funding](../entities/Funding.md).

---

## References
<a name="fn1">\[1\]</a> Source: Law Insider, https://www.lawinsider.com/dictionary/funding-application