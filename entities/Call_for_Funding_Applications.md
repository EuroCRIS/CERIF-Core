# Call for Funding Applications

## Definition
A [Call for Applications](../entities/Call_for_Applications.md) that expects [Funding Applications](../entities/Funding_Application.md).

## Usage notes
The term "Call for Funding Applications" was chosen over "Funding Call" based on a different meaning of the latter in the sector of corporate finance.

## Specialization of
[Call for Applications](../entities/Call_for_Applications.md)

## Attributes
Those of [Call for Applications](../entities/Call_for_Applications.md)

## Relationships
Those inherited from [Call for Applications](../entities/Call_for_Applications.md).

The [Funding](../entities/Funding.md) that is offerred in this Call for Funding Applications is referenced through the derived *of* association from [Resource Offer](../entities/Resource_Offer.md).

## Constraints 
The derived *applications* association from [Call for Applications](../entities/Call_for_Applications.md) can only reference [Funding Applications](../entities/Funding_Application.md).

The derived *of* association from [Resource Offer](../entities/Resource_Offer.md) can only reference a single [Resource](../entities/Resource.md) which must be a [Funding](../entities/Funding.md).
