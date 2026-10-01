# Metadata Set

## Definition
A document that is a set of formalized metadata about some other resource.<sup>[1](#fn1)</sup>

## Usage notes
A metadata set records the properties of its target in a structured form, typically following a specific metadata standard or schema, as opposed to a free-text description.
Any kind of Resource can be described by a metadata set, for instance a piece of Infrastructure or a scholarly publication.

## Specialization of
[Document](../entities/Document.md)

## Attributes
None besides those of [Document](../entities/Document.md).

## Relationships
Besides those of [Document](../entities/Document.md):

<a name="rel__is-metadata-of">is-metadata-of</a> / [has-metadata](../entities/Resource.md#user-content-rel__has-metadata) : A Metadata Set can be metadata of a [Resource](../entities/Resource.md) or Resources.

---
## References
<a name="fn1">\[1\]</a> Source: Adapted from the *DataCite Metadata Schema Documentation for the Publication and Citation of Research Data and Other Research Outputs* (Version 4.7, DataCite e.V., 2026, DOI [10.14454/qdd3-ps68](https://doi.org/10.14454/qdd3-ps68)), which defines the relationType HasMetadata as "Indicates resource A has additional metadata B" and IsMetadataFor as "Indicates additional metadata A for a resource B".
