# Describing Datatypes

## Naming

Datatypes should be named with capitalized first letters of each word in the text with spaces between the words (e.g. [ORCID iD](../datatypes/ORCID_iD.md)). 
The markdown files that describe these datatypes should be named the same, just with underscores instead of spaces (e.g. ../datatypes/ORCID_iD.md).
The datatypes in UML diagrams should be also named with underscores (e.g. "ORCID_iD").
URIs for the datatypes (used in interchange formats) should be constructed using the form with underscores (e.g. "https://w3id.org/cerif/xxx/datatypes/ORCID_iD" – a ficticious URI so far).

## Sections

1. **Definition**: The scope of the datatype and its meaning. (The heading can be ommited of simple types.)
2. **Notes**: An additional description to communicate the purpose of the datatype.
3. **Components**: The list of fields the datatype is composed of. Similar to attributes for [entities](DESCRIBING_ENTITIES.md#sections).
---
4. **Matches**: Describe any external datatypes this datatype matches. 
5. **References**: References to external resources in case these are referenced.

A heading is left out if the section is empty.
