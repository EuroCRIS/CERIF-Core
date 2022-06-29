# Document

## URI
https://w3id.org/cerif2/core/Document

## Definition
A document is a bounded representation of a body of information designed with the capacity (and usually intent) to communicate. 
A document may manifest symbolic, diagrammatic or sensory-representational information.<sup>[1](#fn1)</sup>

## Usage notes
[FIXME]
It is an abstract entity representing the root ancestor for research outputs such as Journal Article, Dataset, Software, Patent, etc. All relatioships which might be linked with any research output, should be generalized by linking the Document entity.

## Specialization of
[Resource](../entities/Resource.md)

## Attributes
<a name="DOI">DOI : [DOI Type](../datatypes/DOI.md)</a>

title : [Multilingual String](../datatypes/Multilingual_String.md)

publication date: [Date](../datatypes/Date.md)

## Relationships
<a name="relab1878d2-60c9-47cb-bac9-09b3b91aa89c">A Document has any number of *[contributorships](../entities/Contribution_to_Document.md#user-content-relab1878d2-60c9-47cb-bac9-09b3b91aa89c)*: instances of [Contribution to Document](../entities/Contribution_to_Document.md).</a>

---
## Matches
1. Close match of [Bibo Document](http://purl.org/ontology/bibo/Document)
2. Close match of [Schema.org CreativeWork](https://schema.org/CreativeWork)

## References
<a name="fn1">\[1\]</a> Source: The Bibo Ontology, http://purl.org/ontology/bibo/Document
