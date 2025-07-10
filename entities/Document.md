# Document

## URI
https://w3id.org/cerif2/core/Document

## Definition
A document is a bounded representation of a body of information designed with the capacity (and usually intent) to communicate. 
A document may manifest symbolic, diagrammatic or sensory-representational information.<sup>[1](#fn1)</sup>

## Usage notes
This class corresponds to the Expression entity in the FRBR hierarchy.<sup>[2](#fn2) section 3.2.2</sup>

The Document class should be used together with a class specifying the type of its contents, such as [Textual Contents](../entities/Textual_Contents.md), [Video Contents](../entities/Video_Contents.md), etc.

Research outputs (such as Journal Articles, Datasets, Software, Patents etc.) are an important subclass of documents.
Any document or set of documents can also serve as input to research.
There are also documents that serve the purpose of planning, organizing and managing research
which are of specific interest for Research Management and Administration.
However, the CERIF Core does not make any of these functions of documents explicit.
Should that be necessary, a particular module should undertake this effort.

## Specialization of
[Resource](../entities/Resource.md)

## Attributes
title : [Multilingual String](../datatypes/Multilingual_String.md)

## Relationships

<a name="rel__has-contribution">has-contribution</a> / [has-target](../entities/Contribution_to_Document.md#user-content-rel__has-target) : A Document any number of [contributions](../entities/Contribution_to_Document.md) that helped it arise.

<a name="rel__provides-evidence-of">provides-evidence-of</a> / [is-evidenced-by](../entities/Expertise_and_Skills_Possession.md#user-content-rel__is-evidenced-by) : A Document can provide evidence of any number of [Expertise and Skills Possessions](../entities/Expertise_and_Skills_Possession.md).

<a name="rel__published-as">published-as</a> / [of](../entities/Document_Publication.md#user-content-rel__of) : A Document can be published as [Document Publication](../entities/Document_Publication.md), once or even multiple times.

<a name="rel__describes-resource-request">describes-resource-request</a> / [is-described-by](../entities/Resource_Request.md#user-content-rel__is-described-by) : A Document can provide description of a [Resource Request](../entities/Resource_Request.md).

<a name="rel__describes-resource-offer">describes-resource-offer</a> / [is-described-by](../entities/Resource_Offer.md#user-content-rel__is-described-by) : A Document can provide description of a [Resource Offer](../entities/Resource_Offer.md).

<a name="rel__presents-decision">presents-decision</a> / [is-expressed-by](../entities/Decision.md#user-content-rel__is-expressed-by) : A Document can present a [Decision](../entities/Decision.md).

<a name="rel__awarded-by">awarded-by</a> / [awarded-for-a-document](../entities/Prize_Award.md#user-content-rel__awarded-for-a-document) : A Document can be awarded by a [Prize Award](../entities/Prize_Award.md).

<a name="rel__proves">proves</a> / [is-evidenced-by](../entities/Activity.md#user-content-rel__is-evidenced-by) : A Document can provide evidence of any number of [Activities](../entities/Activity.md).

<a name="rel__is-part-of">is-part-of</a> / [has-part](../entities/Document.md#user-content-rel__has-part) : A Document can be part of any number of other [Documents](../entities/Document.md). [Contents Location](../datatypes/Contents_Location.md) can be attached to such an association.

<a name="rel__has-part">has-part</a> / [is-part-of](../entities/Document.md#user-content-rel__is-part-of) : A Document can have any number of [Documents](../entities/Document.md) as parts. [Contents Location](../datatypes/Contents_Location.md) can be attached to such an association.

---
## Matches
1. Close match of [Bibo Document](http://purl.org/ontology/bibo/Document)
2. Close match of [Schema.org CreativeWork](https://schema.org/CreativeWork)

## References
<a name="fn1">\[1\]</a> Source: The Bibo Ontology, http://purl.org/ontology/bibo/Document

<a name="fn2">\[2\]</a> Functional requirements for bibliographic records : final report / IFLA Study Group on the Functional Requirements for Bibliographic Records / [International Federation of Library Associations and Institutions. IFLA Universal Bibliographic Control and International MARC Programme, Deutsche Bibliothek, Frankfurt am Main]. ⎯ München : Saur, 1998 (UBCIM publications ; N.S., Vol. 19) ISBN 3-598-11382-X. Available from https://repository.ifla.org/handle/20.500.14598/830 (accessed 2025-06-18)