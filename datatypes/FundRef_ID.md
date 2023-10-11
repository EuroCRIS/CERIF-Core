# The FundRef ID datatype

The datatype that represents [Funder registry](https://www.crossref.org/services/funder-registry/) entries.

It is based on the [String](../datatypes/String.md) datatype with the additional restriction that the string must match the following regular expression<sup>[1](#fn1)</sup>:
```
[1-9]\d+
```

---
## References
<a name="fn1">\[1\]</a> *Crossref funder ID*. Wikidata property. Available from https://www.wikidata.org/wiki/Property:P3153
