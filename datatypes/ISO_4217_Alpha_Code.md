# The ISO 4217 Three-Letter Currency Code datatype

## Definition
The datatype that represents the three-letter codes of currencies in line with the ISO 4217 standard<sup>[1](#fn1)</sup>.

## Based on 
[String](../datatypes/String.md) 

## Restrictions
1. The string must match the following regular expression: `[A-Z]{3}` (three uppercase letters of the English alphabet)
1. The combination of letters must actually identify a currency in the ISO 4217 standard

---
## References
<a name="fn1">\[1\]</a> *ISO 4217 Currency Codes*. International Standards Organization. Available from https://www.iso.org/iso-4217-currency-codes.html
