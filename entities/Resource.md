# Resource

## Definition
Anything that can be used for or in the process of research.

## Usage notes
[Funding](../entities/Funding.md) is one example of a Resource, 
[Expertise+Time+Effort](../entities/Expertise_and_Time_and_Effort.md) of a researcher is another,
[Infrastructure](../entities/Infrastructure.md) is the third one,
[Document](../entities/Document.md) is the fourth one. 

## Relationships

<a name="rel__is-provided-by">is-provided-by</a> / [provides](../entities/Contribution_Statement.md#user-content-rel__provides) : A Resource can be provided in any number of [Contribution Statements](../entities/Contribution_Statement.md).

<a name="rel__is-offered-in">is-offered-in</a> / [offers](../entities/Resource_Offer.md#user-content-rel__offers) : A Resource can be offered in any number of [Resource Offers](../entities/Resource_Offer.md).

<a name="rel__is-requested-in">is-requested-in</a> / [requests](../entities/Resource_Request.md#user-content-rel__requests) : A Resource can be requested in any number of [Resource Requests](../entities/Resource_Request.md).

<a name="rel__has-identifier">has-identifier</a> / [is-assigned-to](../entities/Resource_Identifier.md#user-content-rel__is-assigned-to) : A Resource can have any number of [identifiers](../entities/Resource_Identifier.md).

<a name="rel__is-used-in">is-used-in</a> / [uses](../entities/Resource_Usage_Statement.md#user-content-rel__uses) : When a Resource is used, the usage can be detailed in [Resource Usage Statements](../entities/Resource_Usage_Statement.md). For instance, a funding, a dataset or a piece of equipment can be used in producing a journal article.

<a name="rel__is-part-of">is-part-of</a> / [has-part](../entities/Resource.md#user-content-rel__has-part) : A Resource can be part of any number of other [Resources](../entities/Resource.md).

<a name="rel__has-part">has-part</a> / [is-part-of](../entities/Resource.md#user-content-rel__is-part-of) : A Resource can have any number of other [Resources](../entities/Resource.md) as parts.

---
## Matches


## References
