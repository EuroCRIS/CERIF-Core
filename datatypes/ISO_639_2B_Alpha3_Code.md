# The ISO 639-2 alpha-3 Bibliographic Language Code datatype

## Definition
The datatype that represents the three-letter bibliographic codes of languages in line with the ISO 639-2B standard<sup>[1](#fn1)</sup>.

## Based on 
[String](../datatypes/String.md) 

## Restrictions
1. The string must match the following regular expression `[a-z]{3}` (three lower-case letters of the English alphabet)
1. The combination of letters actually identifiers a language in the ISO 639-2B standard

---
## References
<a name="fn1">\[1\]</a> *Codes for the Representation of Names of Languages*. Library of Congress. Available from https://www.loc.gov/standards/iso639-2/php/English_list.php
