# Textual Document

## Definition
(FIXME) Collection of information records that, in combination, represent a full and up-to-date history of research or scholarly published outputs resulting from, or related to, the person's research activities.<sup>[1](#fn1)</sup>
A resource consisting primarily of words for reading. Examples include books, letters, dissertations, poems, newspapers, articles, archives of mailing lists. <sup>[3](#fn3)</sup>

## Usage notes
(FIXME) A text based scholarly publication or publishing channel that contains results of research. CRISs typically record metadata about scholarly publications from the scope of the CRIS (institutional CRIS for the institution, funder CRIS for the funding it distributed, etc.) in the context of the research projects, infrastructure, funding, organization units and authors/contributors. This entity typically represents the granularity level of a single published item for which attribution information is attached (usually in the form of a list of authors and contributors).<sup>[2](#fn2)</sup> 
This entity is NOT used to represent publishing channels and sources: journals and book series (incl. continuing conference proceedings series).

## Specialization of
[Document](Document.md)

## Attributes
DOI : [DOI_Type](../datatypes/DOI_Type.md) (inherited from [Document](../entities/Document.md))

title : [Multilingual String](../datatypes/MultilingualString.md)

publication date: [Date](../datatypes/Date.md)

access rights: [Textual Document Accessibility Specification](TextualDocumentAccessibilitySpecification.md)

contributorships: List<[Contributorship](Contributorship.md)>

authorships: List<[Authorship](Authorship.md)>

authors: List<[Agent](Agent.md)>

---

## Matches
(FIXME)
1. Close match of [COAR Text](http://vocabularies.coar-repositories.org/documentation/resource_types/#http://purl.org/coar/resource_type/c_18cf)

## References
<a name="fn1">\[1\]</a> Source: CASRAI, http://dictionary.casrai.org/research-personnel-profile/1.1.0/contributions/outputs/publications 

<a name="fn2">\[2\]</a> Source: The OpenAIRE guidelines for CRIS managers, https://openaire-guidelines-for-cris-managers.readthedocs.io/en/v1.1.1/cerif_xml_publication_entity.html

<a name="fn3">\[3\]</a> Source: The COAR resource types vocabulary, http://vocabularies.coar-repositories.org/documentation/resource_types/#http://purl.org/coar/resource_type/c_18cf
