# Describing Entities

## Sections

1. **Definition**: The scope of the entity and its meaning.
2. **Usage Notes**: An additional description to communicate the purpose of the entity.
3. **Attributes**: The list of attributes: properties whose values are simple or composite datatypes.
4. **Relationships**: The relationships that are important for this entity.
5. **Illustrative Diagram**: A UML diagram showing this entity in its context if one is available.
---
6. **Matches**: Describe any external entitites or classes this entity matchees. 
7. **References**: References to external resources in case these are referenced.

A heading is left out if the section is empty.

## Handling inheritance

Inherited attributes and relationships in a subclass are not listed explicitly, 
but the relevant section in the superclass is referenced, 
e.g. [Person relationships](../entities/Person.md#relationships).

## Additional notes

An entity need not document all relationships it is member in.