# The DOI (Digital Object Identifier) datatype

The datatype that represents the [Digital Object Identifiers](https://www.doi.org/).

It is based on the [String](../datatypes/String.md) datatype with the additional restriction that the string must match the following regular expression<sup>[1](#fn1)</sup>:
```
10\.[0-9]{4,}(\.[0-9]+)*/[^  ]
```

---
## References
<a name="fn1">\[1\]</a> Gilmartin, Andrew (2015-08-11). *DOIs and matching regular expressions*. Crossref blog. Available from https://www.crossref.org/blog/dois-and-matching-regular-expressions/
