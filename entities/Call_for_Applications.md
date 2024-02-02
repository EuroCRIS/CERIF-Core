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
[Resource Request](../entities/Resource_Request.md)

## Attributes
deadline: [Date](../datatypes/Date.md) – until when applications can be submitted


## Relationships
<a name="rel__inviter">A Call for Applications always references the *[inviter](../entities/Agent.md#user-content-rel__solicits)*, the [Agent](../entities/Agent.md) that solicits the applications. (Note the role can be called differently in specific contexts.)</a>

<a name="rel__applications">A Call for Applications can reference any number of *[applications](../entities/Application.md#user-content-rel__in-response-to)*, instances of [Application](../entities/Application.md), that are formed with a reference to it.

A Call for Applications can optionally reference its *contents*, the [Document](../entities/Document.md) that details the invitation.

---
## References
<a name="fn1">\[1\]</a> Source: The Oxford Dictionary, https://www.lexico.com/definition/invitation