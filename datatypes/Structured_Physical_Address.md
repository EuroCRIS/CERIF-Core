# Structured Postal Address

## Definition
This data type is used when a structured representation of physical address is used.

## Notes
The main intended usage of multiple strings for different languages is to allow the representation of the addresses in different scripts, allowing for transliterations.

## Specialization of
[Physical Address](../datatypes/Physical_Address.md)

## Components
- those inherited from [Physical Address](../datatypes/Physical_Address.md#components)
- street : [Multilingual String](../datatypes/Multilingual_String.md)
- city : [Multilingual String](../datatypes/Multilingual_String.md)
- ZIP code: [Multilingual String](../datatypes/Multilingual_String.md)
- stateOrProvince: [Multilingual String](../datatypes/Multilingual_String.md)

---
## Matches
1. Close match of [Schema.org Postal address](https://schema.org/PostalAddress)
