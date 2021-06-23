# TextualDocument

## Definition
Collection of information records that, in combination, represent a full and up-to-date history of research or scholarly published outputs resulting from, or related to, the person's research activities.<sup>[1](#fn1)</sup>

## Usage notes
A text based scholarly publication or publishing channel that contains results of research. CRISs typically record metadata about scholarly publications from the scope of the CRIS (institutional CRIS for the institution, funder CRIS for the funding it distributed, etc.) in the context of the research projects, infrastructure, funding, organization units and authors/contributors. This entity typically represents the granularity level of a single published item for which attribution information is attached (usually in the form of a list of authors and contributors).<sup>[2](#fn2)</sup> 
This entity is NOT used to represent publishing channels and sources: journals and book series (incl. continuing conference proceedings series).

## Specialization of
[Document](Document.md)

## Attributes
* DOI : [DOI_Type](../datatypes/DOI_Type.md)
* title : [MultilingualString](../datatypes/MultilingualString.md)
* PublicationDate: [Date](../datatypes/Date.md)
* AccessRights: [Textual Document Accessibility Specification](TextualDocumentAccessibilitySpecification.md)

## Relationships
* contributorships: List<[Contributorship](Contributorship.md)>
* authorships: List<[Authorship](Authorship.md)>
* authors: List<[Agent](Agent.md)>

---

## Matches
1. Close match of [FOAF Person](http://xmlns.com/foaf/spec/#term_Person) (identified by URI http://xmlns.com/foaf/0.1/Person), which is also used in VIVO
2. Narrow match of [Schema.org Person](https://schema.org/Person)

## References
1. <a name="fn1"></a> Source: CASRAI, http://dictionary.casrai.org/research-personnel-profile/1.1.0/contributions/outputs/publications 
2. <a name="fn2"></a> Source: The OpenAIRE guidelines for CRIS managers, https://openaire-guidelines-for-cris-managers.readthedocs.io/en/v1.1.1/cerif_xml_publication_entity.html
