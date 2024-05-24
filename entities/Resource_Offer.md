# Resource Offer

## Definition
An offer of a [Resource](../entities/Resource.md) for usage.

## Relationships

<a name="rel__offers">offers</a> / [is-offered-in](../entities/Resource.md#user-content-rel__is-offered-in) : A Resource Offer specifies the [Resource](../entities/Resource.md) that is the subject of the offer.

<a name="rel__has-condition">has-condition</a> / [applies-to](../entities/Condition.md#user-content-rel__applies-to) : A Resource Offer can have any number of [Conditions](../entities/Condition.md) that must be satisfied for the Resource Offer to be applicable.

<a name="rel__is-described-by">is-described-by</a> / [describes-resource-offer](../entities/Document.md#user-content-rel__describes-resource-offer) : A Resource Offer can be described by any number of [Documents](../entities/Document.md).

