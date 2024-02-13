# Application

## Definition
A formal request to be considered for a position or to be allowed to do or have something, submitted to an authority, institution, or organization.<sup>[1](#fn1)</sup>

## Usage notes
Any kind of proposal that is brought to (or intended for) the attention of a relevant [Agent](../entities/Agent.md).
Examples include job applications, grant proposals (i.e. requests for funding), submissions of manuscripts to journals, applications to access a particular research infrastructure, applications for a permission to perform some research (e.g. getting the approval of an ethical committee), nominations (of either oneself or someone else) for awards etc.

Some applications reference an explicit [Call for Applications](../entities/Call_for_Applications.md) (e.g. a funding call or call for nominations for an award), while other applications are based on an implicit invitation (e.g. a manuscript submitted to a journal).

Applications, when submitted, typically enter some kind of process which results in an [Evaluation Outcome](../entities/Evaluation_Outcome.md).
Based on one or more such outcomes, a [Decision](../entities/Decision.md) is made.

This entity represents the act of applying rather than the application [Document](../entities/Document.md). 

Subclasses specify the nature of the application as well the details. 
An application typically contains the request (what is proposed to happen) and its justification (what benefits it would bring).

## Attributes

application date: [Date](../datatypes/Date.md)

## Relationships

<a name="rel__has-applicant">An Application always has the *[applicant](../entities/Agent.md#user-content-rel__is-the-applicant-in)*, the [Agent](../entities/Agent.md) that is applying. (Note the role can be called differently in specific contexts.)</a>

<a name="rel__has-addressee">An application always references its *[addressee](../entities/Agent.md#user-content-rel__be-the-addressee-of)*, the [Agent](../entities/Agent.md) that is expected to consider the application. (Note the role can be called differently in specific contexts.)</a>

<a name="rel__in-response-to">An application can reference the *[in-response-to](../entities/Call_for_Applications.md#user-content-rel__applications)* [Call for Applications](../entities/Call_for_Applications.md), the invitation that this application is responding to.</a>

<a name="rel__contents">A Call for Applications can optionally reference its *[contents](../entities/Document.md#user-content-rel__be-contents-for)*, the [Document](../entities/Document.md) that details the invitation.</a>

---

## References
<a name="fn1">\[1\]</a> Source: The Oxford Dictionary, https://www.lexico.com/definition/application