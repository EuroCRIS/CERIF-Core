# The ISO 15924 Four-letter Script Code datatype

The datatype that represents the four-letter codes of scripts in line with the ISO 15924 standard<sup>[1](#fn1)</sup>.

It is based on the [String](../datatypes/String.md) datatype with the additional restrictions:
- the string must match the following regular expression `[A-Z][a-z]{3}` (one uppercase letter followed by three lower-case letters of the English alphabet)
- the combination of letters actually identifiers a language in the ISO 639-2T standard

---
## References
<a name="fn1">\[1\]</a> *Codes for the representation of names of scripts*. International Standards Organization. Available from https://www.unicode.org/iso15924/codelists.html
