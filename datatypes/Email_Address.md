# Email Address

## Definition
An email address assigned to an [Agent](../entities/Organisation_Unit.md). 

## Specialization of
[Electronic Address](../datatypes/Electronic_Address.md)

## Based on
[URI](../datatypes/URI.md)

## Restrictions
1. The URI must start with `mailto:`
2. The rest of the URI must match the regular expression<sup>[1](#fn1)</sup>: `^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$`

---
## Matches
1. Close match of [Schema.org Email](https://schema.org/email)

## References
<a name="fn1">\[1\]</a> Email address validation https://regexr.com/3e48o
