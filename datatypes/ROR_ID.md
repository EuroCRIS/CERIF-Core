# ROR ID Type

## Definition
The datatype that represents the [ROR](https://ror.org/) identifier.

## Based on 
[String](../datatypes/String.md) 

## Restrictions
1. The string must match the regular expression<sup>[1](#fn1)</sup>: `https://ror\.org/0[0-9a-hjkmnp-tv-z]{6}(0[2-9]|[1-8][0-9]|9[0-8])`
1. The last two digits are the modulo 97-10 checksum<sup>[2](#fn2)</sup> of the number formed by the preceding six digits

---
## References
<a name="fn1">\[1\]</a> *Facts*. ROR. Available from https://ror.org/facts/

<a name="fn2">\[2\]</a> *ISO/IEC 7064:2003. Information technology — Security techniques — Check character systems.* International Standards Organization.
