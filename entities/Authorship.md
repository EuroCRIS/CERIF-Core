# Authorship

## Definition
Structured information about linking an [Agent](../entities/Agent.md) or any of its specializations ([Person](../entities/Person.md), [Organisation Unit](../entities/Organisation_Unit.md)) with a [Document](../entities/Document.md) or any of its specializations. Authorship implies responsibility and accountability of an Agent for published Document.

## Specialization of
[Contribution to Document](../entities/Contribution_to_Document.md)

## Attributes
Those inherited from [Contribution to Document](../entities/Contribution_to_Document.md#attributes) plus:

corresponding author flag: [Boolean](../datatypes/Boolean.md) – indicates whether this author is responsible for correspondence (true)

## Relationships
Beside those inherited from [Contribution to Document](https://github.com/EuroCRIS/CERIF-Core/blob/main/entities/Contribution_to_Document.md#relationships) and its predecessors, there is also a link to *author*: an instance of [Agent](https://github.com/EuroCRIS/CERIF-Core/blob/main/entities/Agent.md)
replacing the derived *has-actor* link from [Activity](https://github.com/EuroCRIS/CERIF-Core/blob/main/entities/Activity.md#user-content-rel__has-actor).

---
## Matches
1. Close match with the **[author](https://sparontologies.github.io/pro/current/pro.html#d4e543)** named individual from the SPAR PRO Ontology.<sup>[1](#fn1)</sup>

## References
<a name="fn1">\[1\]</a> Author. In: *PRO, the Publishing Roles Ontology*. Shotton, D. and Peroni, S. Available: https://sparontologies.github.io/pro/current/pro.html
