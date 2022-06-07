# Invitation for Applications

## Definition
A written or verbal request inviting someone to go somewhere or to do something,<sup>[1](#fn1)</sup>
namely, submit an [Application](../entities/Application.md).

## Usage notes
An invitation for applications aims to solicit a response: [Applications](../entities/Application.md).
It is typically published or circulated among potential candidates.

Examples include job advertisements, funding calls (requests for grant proposals), calls for nominations for an award etc.

## Attributes

deadline: [Date](../datatypes/Date.md) – until when applications can be submitted

Subclasses specify the nature of the invitation for application as well the details. 
An invitation for applications typically covers a goal (what is intended), a means (what is requested), an offer (what is offered to support the goal) and the guidelines for preparing and submitting applications.

## Relationships

An invitation for applications always references the *inviter*, the [Agent](../entities/Agent.md) that solicits the applications. (Note the role can be called differently in specific contexts.)

An invitation for application can optionally reference its *contents*, the [Document](../entities/Document.md) that details the invitation.

---

## Notes
The term "Offer" was considered as an alternative for naming this entity. However, it was not used as it only covers one aspect of the whole situation.

## References
<a name="fn1">\[1\]</a> Source: The Oxford Dictionary, https://www.lexico.com/definition/invitation