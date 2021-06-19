# ORCID iD Type

The datatype that represents the [ORCID](https://orcid.org/) identifier.

It is based on the xsd:string datatype with the additional restriction by the following regular expression:
```
https://orcid\.org/0000-000(1-[5-9]|2-[0-9]|3-[0-4])[0-9]{3}-[0-9]{3}[0-9X]
```
