# Describing Datatypes

## Naming

Datatypes should be named with capitalized first letters of each word in the text with spaces between the words (e.g. [ORCID iD](../datatypes/ORCID_iD.md)). 
The markdown files that describe these datatypes should be named the same, just with underscores instead of spaces (e.g. ../datatypes/ORCID_iD.md).
The datatypes in UML diagrams should be also named with underscores (e.g. "ORCID_iD").
URIs for the datatypes (used in interchange formats) should be constructed using the form with underscores (e.g. "https://w3id.org/cerif/xxx/datatypes/ORCID_iD" – a ficticious URI so far).

## Sections

A heading is left out if the section is empty.

### For datatypes with components (xsd:complexType)
1. **Definition**: The scope of the datatype and its meaning.
2. **Notes**: An additional description to communicate the purpose of the datatype.
3. **Specialization of**: The link to the super datatype (the datatype this type extends)
4. **Components**: The list of fields the datatype is composed of. Similar to attributes for [entities](DESCRIBING_ENTITIES.md#sections).
5. **Restrictions**: An enumerated list of restrictions that apply between components (e.g. The *start date* and *end date* must have the same granularity)
---
6. **Matches**: Describe any external datatypes this datatype matches. 
7. **References**: References to external resources in case these are referenced.

Use the [template file](./TEMPLATE_DATATYPE_COMPLEX.md) as a starting point.

### For datatypes without components (xsd:simpleType)
1. **Definition**: The scope of the datatype and its meaning.
2. **Notes**: An additional description to communicate the purpose of the datatype.
3. **Specialization of**: The link to the super datatype (the datatype this type extends)
4. **Based on**: The link to the base datatype (the datatype this type restricts)
5. **Restrictions**: An enumerated list of restrictions that apply (e.g. maximum length, regular expression, possibly also algorithmic restrictions such as checksums)
6. **Examples**: A list of examples
---
7. **Matches**: Describe any external datatypes this datatype matches.
8. **References**: References to external resources in case these are referenced.

Use the [template file](./TEMPLATE_DATATYPE_SIMPLE.md) as a starting point.