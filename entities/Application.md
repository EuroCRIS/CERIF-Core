# Application

## Definition
A formal request to be considered for a position or to be allowed to do or have something, submitted to an authority, institution, or organization.<sup>[1](#fn1)</sup>

## Usage notes
Any kind of proposal that is brought to (or intended for) the attention of a relevant [Agent](../entities/Agent.md).
Examples include job applications, grant proposals (i.e. requests for funding), submissions of manuscripts to journals, applications to access a particular research infrastructure, applications for a permission to perform some research (e.g. getting the approval of an ethical committee), nominations (of either oneself or someone else) for awards etc.

Some applications reference an explicit [invitation to apply](../entities/Invitation_for_Applications.md) (e.g. a funding call or call for nominations for an award), while other applications are based on an implicit invitation (e.g. a manuscript submitted to a journal).

Applications, when submitted, enter some kind of an evaluation [Process](../entities/Process.md).

This entity represents the act of applying rather than the application [Document](../entities/Document.md). 

## Attributes

application date: [Date](../datatypes/Date.md)

Subclasses specify the nature of the application as well the details. 
An application typically contains the request (what is proposed to happen) and its justification (what benefits it would bring).

## Relationships

An application always references the *applicant*, the [Agent](../entities/Agent.md) that makes the application. (Note the role can be called differently in specific contexts.)

An application always references its *addressee*, the [Agent](../entities/Agent.md) that is expected to consider the application. (Note the role can be called differently in specific contexts.)

An application can reference the *in-response-to* [Invitation for Applications](../entities/Invitation_for_Applications.md), the invitation this application is responding to.

An application can optionally reference its *contents*, the [Document](../entities/Document.md) that details the application.

---

## References
<a name="fn1">\[1\]</a> Source: The Oxford Dictionary, https://www.lexico.com/definition/application