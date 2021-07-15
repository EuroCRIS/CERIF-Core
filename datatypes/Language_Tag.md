# Language Tag

## Definition
A string used as an identifier for a language, possibly expressing additional information about the local variant of the language, script, or other aspects.  

## Notes
The syntax and semantics of language tags is specified by the BCP 47 standard<sup>[1](#fn1)</sup>.

Language tags are used as language qualifiers in [Multilingual Strings](../datatypes/Multilingual_String.md).

## Syntax

A code for [language](../entities/Language.md), optionally followied by codes 
for a [script](../entities/Script.md), a [country](../entities/Country.md) or for other aspects, separated by dashes (`-`).

Examples:
- `en` - English
- `de-AT` - German as used in Austria
- `sr-Cyrl` - Serbian written in Cyrillic 
- `gr-Latn-CY` - Greek as used on Cyprus written using the Latin alphabet

---
## References
<a name="fn1">\[1\]</a> Source: A. Phillips; M. Davis. *Tags for Identifying Languages*. IETF. September 2009. IETF Best Current Practice. Available from https://tools.ietf.org/search/bcp47 
