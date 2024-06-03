# Resource Request

## Definition
A request to use a [Resource](../entities/Resource.md).

## Usage notes
Any kind of proposal that is brought to (or intended for) the attention of a relevant [Agent](../entities/Agent.md).
Examples include job applications, grant proposals (i.e. requests for funding), submissions of manuscripts to journals, applications to access a particular research infrastructure, applications for a permission to perform some research (e.g. getting the approval of an ethical committee), nominations (of either oneself or someone else) for awards etc.

Some resource requests might be more formal representing formal applications referencing an explicit [Call for Applications](../entities/Call_for_Applications.md) (e.g. a funding call or call for nominations for an award), while other resource request are based on an implicit invitation (e.g. a manuscript submitted to a journal).

Resource requests, when submitted, typically enter some kind of process which results in an [Evaluation Outcome](../entities/Evaluation_Outcome.md).
Based on one or more such outcomes, a [Decision](../entities/Decision.md) is made.

This entity represents the act of applying rather than the application [Document](../entities/Document.md).


## Attributes
request date: [Date](../datatypes/Date.md)

## Relationships

<a name="rel__requests">requests</a> / [is-requested-in](../entities/Resource.md#user-content-rel__is-requested-in) : A Resource Request specifies the [Resource](../entities/Resource.md) that is the subject of the request.

<a name="rel__has-requester">has-requester</a> / [is-requester-in](../entities/Agent.md#user-content-rel__is-requester-in) : A Resource Request always has the requester: the [Agent](../entities/Agent.md) that is requesting a resource. (Note that the role can be called differently in specific contexts, for instance applicant.)

<a name="rel__has-addressee">has-addressee</a> / [is-addressee-of](../entities/Agent.md#user-content-rel__is-addressee-of) : A Resource Request always references its addressee, the [Agent](../entities/Agent.md) that is expected to consider the resource request. (Note the role can be called differently in specific contexts.)

<a name="rel__is-described-by">is-described-by</a> / [describes-resource-request](../entities/Document.md#user-content-rel__describes-resource-request) : A Resource Request can optionally reference its contents, the [Document](../entities/Document.md) that details the resource request.

<a name="rel__revises">revises</a> : A Resource Request can revise the another [Resource Request](../entities/Document.md) that wasn't successful in the previous round.

<a name="rel__has-evaluation-outcome">has-evaluation-outcome</a> / [is-an-evaluation-outcome-of](../entities/Evaluation_Outcome.md#user-content-rel__is-an-evaluation-outcome-of) : A Resource Request can have some kind of process which results in an [Evaluation Outcome](../entities/Evaluation_Outcome.md).

<a name="rel__has-decision">has-decision</a> / [is-a-decision-on](../entities/Decision.md#user-content-rel__is-a-decision-on) : A Resource Request can have a [Decision](../entities/Decision.md) about accepting or refusing request made by addressee.
