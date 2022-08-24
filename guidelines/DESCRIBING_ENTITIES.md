# Describing Entities

## Naming

Entities should be named with capitalized first letters of each word in the text with spaces between the words (e.g. [Affiliation Statement](../entities/Affiliation_Statement.md)). 
The markdown files that describe these entities should be named the same, just with underscores instead of spaces (e.g. ../entities/Affiliation_Statement.md).
The entities in UML diagrams should be also named with underscores (e.g. "Affiliation").
URIs for the entity types (used in interchange formats) should be constructed using the form with underscores (e.g. "https://w3id.org/cerif/xxx/entities/Affilation_Statement" – a ficticious URI so far).

Attributes and relationship ends should be called with lowercase letters in the text with spaces between the words (e.g. "web site URL" or "contribution statements").
In UML diagrams and in interchange formats the camel-case notation should be used (so "webSiteURL" or "contributionStatements").

## Sections

1. **Definition**: The scope of the entity and its meaning.
2. **Usage Notes**: An additional description to communicate the purpose of the entity.
3. **Specialization of**: The link to the super class
4. **Attributes**: The list of attributes: properties whose values are simple or composite [datatypes](../guidelines/DESCRIBING_DATATYPES.md). This section can introduce new attributes and it can also contain guidelines on using attributes from superclasses. All attributes are considered optional by default.
5. **Relationships**: The relationships that are important for this entity. This section can add new relationships and it can also contain guidelines on using relationships from superclasses. The default cardinality of a relationship is "1".
6. **Constraints**: Any constraints a subclass makes on its superclasses.
7. **Illustrative Diagram**: A UML diagram showing this entity in its context if one is available.
---
8. **Matches**: Describe any external entities or classes this entity matches. 
9. **References**: References to external resources in case these are referenced.

A heading is left out if the section is empty.

Use the [template file](./TEMPLATE_ENTITY.md) as a starting point.

## Handling inheritance

Inherited attributes and relationships in a subclass are not listed explicitly, 
but the relevant section in the superclass is referenced, 
e.g. [Document relationships](../entities/Document.md#relationships).

## Additional notes

An entity should document all relationships it is member in, as long as the other end is in the same module or one of its dependencies (incl. the Core).

