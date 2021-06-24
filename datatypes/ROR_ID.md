# ROR ID Type

The datatype that represents the [ROR](https://ror.org/) identifier.

It is based on the [String](../datatypes/String.md) datatype with the additional restriction that the string must match the following regular expression<sup>[1](#fn1)</sup>:
```
https://ror\.org/0[0-9a-hjkmnp-tv-z]{6}(0[2-9]|[1-8][0-9]|9[0-8])
```

---
## References
<a name="fn1">\[1\]</a> *Facts*. ROR. Available from https://ror.org/facts/
