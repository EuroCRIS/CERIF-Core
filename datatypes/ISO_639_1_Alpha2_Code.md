# The ISO 639-1 alpha-2 Language Code datatype

## Definition
The datatype that represents the two-letter codes of languages in line with the ISO 639-1 standard<sup>[1](#fn1)</sup>.

## Based on
[String](../datatypes/String.md) 

## Restrictions
1. The string must match the following regular expression `[a-z]{2}` (two lower-case letters of the English alphabet)
1. The combination of letters actually identifiers a language in the ISO 639-1 standard

---
## References
<a name="fn1">\[1\]</a> *Codes for the Representation of Names of Languages*. Library of Congress. Available from https://www.loc.gov/standards/iso639-2/php/English_list.php
