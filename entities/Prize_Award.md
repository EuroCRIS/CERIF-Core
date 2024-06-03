# Prize Award

## Definition
A [prize](../entities/Prize.md) or a sum of money that a person or organization is given for an achievement.<sup>[1](#fn1)</sup>

## Attributes

statement : [Multilingual String](../datatypes/Multilingual_String.md)
date : [Date](../datatypes/Date.md)

## Relationships

<a name="rel__awards">awards</a> / [is-awarded-as](../entities/Prize.md#user-content-rel__is-awarded-as) : A Prize Award awards a specific [Prize](../entities/Prize.md).

<a name="rel__has-recipient">has-recipient</a> / [is-recipient-of](../entities/Agent.md#user-content-rel__is-recipient-of) : A Prize Award can have any number of recipients, instances of [Agent](../entities/Agent.md).

<a name="rel__awarded-for-a-document">awarded-for-a-document</a> / [awarded-by](../entities/Document.md#user-content-rel__awarded-by) : A Prize can be awarded for a [Document](../entities/Document.md).

<a name="rel__awarded-for-a-project">awarded-for-a-project</a> / [awarded-by](../entities/Project.md#user-content-rel__awarded-by) : A Prize can be awarded for a [Project](../entities/Project.md).

<a name="rel__awarded-for-an-event">awarded-for-an-event</a> / [awarded-by](../entities/Event.md#user-content-rel__awarded-by) : A Prize can be awarded for an [Event](../entities/Event.md).

<a name="rel__awarded-by-whom">awarded-by-whom</a> / [awards-prize](../entities/Event.md#user-content-rel__awards-prize) : A Prize can be awarded by any number of [Agents](../entities/Agent.md).

<a name="rel__has-contribution">has-contribution</a> / [has-target](../entities/Contribution_to_Prize_Awards.md#user-content-rel__has-target) : A Prize Award can have any number of [contributions](../entities/Contribution_to_Prize_Awards.md) that help its organization.


---
## Matches
1. Close match of [VIVO Award Receipt](http://vivoweb.org/ontology/core#AwardReceipt)

## References
<a name="fn1">\[1\]</a> Source: The Cambridge Dictionary of English. https://dictionary.cambridge.org/dictionary/english/award
