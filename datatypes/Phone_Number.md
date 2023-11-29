# Phone Number

## Definition
A phone number assigned to an [Agent](../entities/Organisation_Unit.md). 

## Specialization of
[Electronic Address](../datatypes/Electronic_Address.md)

## Restrictions
1. The uri component must match RFC 3966 specification <sup>[1](#fn1)</sup>

## Examples
- `tel:+1-201-555-0123` - This URI points to a full phone number. 
- `tel:863-1234;phone-context=+1-914-555` - The URI describes a local
  phone number that is valid within a particular phone prefix.

---
## Matches
1. Close match of [Schema.org Phone](https://schema.org/telephone)

## References
<a name="fn1">\[1\]</a> The tel URI for Telephone Numbers https://datatracker.ietf.org/doc/html/rfc3966
