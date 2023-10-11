# Structured Postal Address

## Definition

This data type is used when a structured representation of postal address is used.

## Specialization of
[Postal Address](../datatypes/Postal_Address.md)

## Notes

The main intended usage of multiple strings for different languages is to allow the representation of the addresses in different scripts, allowing for transliterations.

## Components

Beside those inherited from [Postal Address](../datatypes/Postal_Address.md#components), there are also the following components:

- street : [Multilingual String](../datatypes/Multilingual_String.md)
- city : [Multilingual String](../datatypes/Multilingual_String.md)
- ZIP code: [Multilingual String](../datatypes/Multilingual_String.md)
- stateOrProvince: [Multilingual String](../datatypes/Multilingual_String.md)


