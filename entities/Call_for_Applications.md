# Call for Applications

## Definition
A written or verbal request inviting someone to go somewhere or to do something,<sup>[1](#fn1)</sup>
namely, submit an [Application](../entities/Application.md).

## Usage notes
A Call for Applications aims to solicit a response: [Applications](../entities/Application.md).
It is typically published or circulated among potential candidates.

Examples include job advertisements, funding calls (requests for grant proposals), calls for nominations for an award etc.

Subclasses specify the nature of the invitation for application as well the details. 
A Call for Applications typically covers a goal (what is intended), a means (what is requested), an offer (what is offered to support the goal) and the guidelines for preparing and submitting applications.

## Specialization of
[Resource Offer](../entities/Resource_Offer.md)

## Attributes
deadline: [Date](../datatypes/Date.md) – until when applications can be submitted


## Relationships

<a name="rel__is-solicited-by">is-solicited-by</a> / [solicits](../entities/Agent.md#user-content-rel__solicits) : A Call for Applications is solicited by an [Agent](../entities/Agent.md).

<a name="rel__has-application">has-application</a> / [in-response-to](../entities/Application.md#user-content-rel__in-response-to) : A Call for Applications can reference the [Applications](../entities/Application.md) that make a reference to it.

---
## References
<a name="fn1">\[1\]</a> Source: The Oxford Dictionary, https://www.lexico.com/definition/invitation