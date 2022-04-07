# ISNI ID Type

The datatype that represents the [ISNI](https://isni.org/) identifier (International Standard Name Identifier).

It is based on the [String](../datatypes/String.md) datatype with the following restriction:
- the string must match the following regular expression: `https://isni\.org/isni/[0-9]{15}[0-9X]`<sup>[1](#fn1)</sup>
- the last character is the checksum character of the previous 15 digits (discarding the hyphens) calculated by the MOD11-2 algorithm<sup>[2](#fn2)</sup>

---
## References
<a name="fn1">\[1\]</a> ISNI. *Technical Documentation*. Available from https://isni.org/page/technical-documentation/
Note: While ISNI does not publish any display guidelines, the URL form appears to be the most straightforward one.

<a name="fn2">\[2\]</a> *ISO/IEC 7064:2003. Information technology — Security techniques — Check character systems.* International Standards Organization.
