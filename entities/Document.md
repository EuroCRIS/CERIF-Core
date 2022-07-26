# Document

## URI
https://w3id.org/cerif2/core/Document

## Definition
A document is a bounded representation of a body of information designed with the capacity (and usually intent) to communicate. 
A document may manifest symbolic, diagrammatic or sensory-representational information.<sup>[1](#fn1)</sup>

## Usage notes
Research outputs (such as Journal Articles, Datasets, Software, Patents etc.) are an important subclass of documents.
Any document or set of documents can also serve as input to research.
There are also documents that serve the purpose of planning, organizing and managing research
which are of specific interest for Research Management and Administration.
However, the CERIF Core does not make any of these functions of documents explicit.
Should that be necessary, a particular module should undertake this effort.

## Specialization of
[Resource](../entities/Resource.md)

## Attributes
<a name="DOI">DOI : [DOI Type](../datatypes/DOI.md)</a>

title : [Multilingual String](../datatypes/Multilingual_String.md)

publication date: [Date](../datatypes/Date.md) in case the document has gone through publication

## Relationships
<a name="relab1878d2-60c9-47cb-bac9-09b3b91aa89c">A Document has any number of *[contributorships](../entities/Contribution_to_Document.md#user-content-relab1878d2-60c9-47cb-bac9-09b3b91aa89c)*: instances of [Contribution to Document](../entities/Contribution_to_Document.md).</a>

<a name="rel__prove_Expertise_and_Skills_Posession">A Document can *[prove](../entities/Expertise_and_Skills_Posession.md#user-content-rel__is-evidenced-by)* any number of [Posessions of Expertise and Skills](../entities/Expertise_and_Skills_Posession.md).</a>

<a name="rel__prove_Activity">A Document can *[prove_Activity](../entities/Activity.md#user-content-rel__is-evidenced-by)* any number of [Activities](../entities/Activity.md).</a>

---
## Matches
1. Close match of [Bibo Document](http://purl.org/ontology/bibo/Document)
2. Close match of [Schema.org CreativeWork](https://schema.org/CreativeWork)

## References
<a name="fn1">\[1\]</a> Source: The Bibo Ontology, http://purl.org/ontology/bibo/Document
