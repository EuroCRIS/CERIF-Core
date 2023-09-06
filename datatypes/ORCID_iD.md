# ORCID iD Type

The datatype that represents the [ORCID](https://orcid.org/) identifier.

It is based on the [String](../datatypes/String.md) datatype with the following restriction:
- the string must match one of the following regular expressions:<sup>[1](#fn1)</sup> 
  - `https://orcid\.org/0000-000((1-[5-9]|2-[0-9]|3-[0-4])[0-9]{3}-[0-9]{3}[0-9X]|3-5000-0001)`
  - `https://orcid\.org/0009-00(0[0-9]-[0-9]{4}-[0-9]{3}[0-9X]|10-0000-0000)`
- the last character is the checksum character of the previous 15 digits (discarding the hyphens) calculated by the MOD11-2 algorithm<sup>[2](#fn2)</sup>

---
## References
<a name="fn1">\[1\]</a> ORCID Support. _Structure of the ORCID Identifier_. Available from https://support.orcid.org/hc/en-us/articles/360006897674-Structure-of-the-ORCID-Identifier

<a name="fn2">\[2\]</a> *ISO/IEC 7064:2003. Information technology — Security techniques — Check character systems.* International Standards Organization.
