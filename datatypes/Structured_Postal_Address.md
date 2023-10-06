# Structured Postal Address

This data type is used when a structured representation of postal address is used.

## Components

- street : [Multilingual String](../datatypes/Multilingual_String.md)
- city : [Multilingual String](../datatypes/Multilingual_String.md)
- ZIP code: [Multilingual String](../datatypes/Multilingual_String.md)
- stateOrProvince: [Multilingual String](../datatypes/Multilingual_String.md)

## Notes

The main intended usage of multiple strings for different languages is to allow the representation of the addresses in different scripts, allowing for transliterations.
This is a subtype of [Postal Address](../datatypes/Postal_Address.md), and therefore it also includes inherited country component.  

