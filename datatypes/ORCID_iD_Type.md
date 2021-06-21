# ORCID iD Type

The datatype that represents the [ORCID](https://orcid.org/) identifier.

It is based on the [String](./String.md) datatype with the additional restriction that the string must match the following regular expression<sup>[1](#fn1)</sup>:
```
https://orcid\.org/0000-000(1-[5-9]|2-[0-9]|3-[0-4])[0-9]{3}-[0-9]{3}[0-9X]
```

---
## References
<a name="fn1">\[1\]</a> ORCID Support. _Structure of the ORCID Identifier_. Available from https://support.orcid.org/hc/en-us/articles/360006897674-Structure-of-the-ORCID-Identifier
