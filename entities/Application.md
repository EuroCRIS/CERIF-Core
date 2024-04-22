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

<a name="rel__has-applicant">has-applicant</a> / [is-applicant-in](../entities/Agent.md#user-content-rel__is-applicant-in) : An Application always has the applicant: the [Agent](../entities/Agent.md) that is applying. (Note that the role can be called differently in specific contexts.)

<a name="rel__has-addressee">has-addressee</a> / [is-addressee-of](../entities/Agent.md#user-content-rel__is-addressee-of) : An Application always references its addressee, the [Agent](../entities/Agent.md) that is expected to consider the application. (Note the role can be called differently in specific contexts.)

<a name="rel__in-response-to">in-response-to</a> / [has-application](../entities/Call_for_Applications.md#user-content-rel__has-application) : An Application can optionally reference the [Call for Applications](../entities/Call_for_Applications.md) it responds to.

<a name="rel__has-contents">has-contents</a> : An Application can optionally reference its contents: the [Document](../entities/Document.md) that details the Application.


---

## References
<a name="fn1">\[1\]</a> Source: The Oxford Dictionary, https://www.lexico.com/definition/application