# The ISO 639-1 alpha-2 Language Code datatype

The datatype that represents the two-letter codes of languages in line with the ISO 639-1 standard<sup>[1](#fn1)</sup>.

It is based on the [String](../datatypes/String.md) datatype with the following additional restrictions:
- the string must match the following regular expression `[a-z]{2}` (two lower-case letters of the English alphabet)
- the combination of letters actually identifiers a language in the ISO 639-1 standard

---
## References
<a name="fn1">\[1\]</a> *Codes for the Representation of Names of Languages*. Library of Congress. Available from https://www.loc.gov/standards/iso639-2/php/English_list.php
