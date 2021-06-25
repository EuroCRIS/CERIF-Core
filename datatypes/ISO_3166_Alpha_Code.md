# The ISO 3166 Two-Letter Country Code datatype

The datatype that represents the two-letter codes of countries in line with the ISO 3166 standard<sup>[1](#fn1)</sup>.

It is based on the [String](../datatypes/String.md) datatype with the following additional restrictions:
- the string must match the following regular expression: `[A-Z]{2}` (two uppercase letters of the English alphabet)
- the combination of letters must actually identify a country in the ISO 3166 standard

---
## References
<a name="fn1">\[1\]</a> *ISO 3166 Country Codes*. International Standards Organization. Available from https://www.iso.org/iso-3166-country-codes.html
