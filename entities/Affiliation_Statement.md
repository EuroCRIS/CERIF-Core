# Affiliation Statement

## Definition

A statement of a connection between a [Person](../entities/Person.md) and an [Organisation Unit](../entities/Organisation_Unit.md).

## Usage notes

An affiliation statement expresses the fact that there is some kind of connection. 
Subclasses can be more specific about the type of the connection.

Affiliation statements can have various levels of specificity and structure.
They can exist as standalone objects (typically where an institution makes statements about its staff) or in connection to a specific [Activity](../entities/Activity.md) where it allows to specify the affiliations or representations to be specified.

## Attributes

display affiliation: [String](../datatypes/String.md) – the raw string form of the affiliation as found e.g. in scholarly publications; more structured statements make use of the *institution* and/or *organisation unit* relationships below

display person name: [String](../datatypes/String.md) – the raw string form of the name of the person as found e.g. in scholarly publications; more structured statements make use of the *person* relationship below

contacts: List<[Contact Information](../datatypes/Contact_Information.md)>

position    : [Position](../datatypes/Position.md)

## Relationships

<a name="rel__has-institution">has-institution</a> / [is-the-institution-in](../entities/Organisation_Unit.md#user-content-rel__is-the-institution-in) : An Affiliation Statement has institution, an instance of [Organisation Units](../entities/Organisation_Unit.md).

<a name="rel__has-organisation-unit">has-organisation-unit</a> / [is-the-organisation-unit-in](../entities/Organisation_Unit.md#user-content-rel__is-the-organisation-unit-in) : An Affiliation Statement can optionally have an organisation unit, an instance of [Organisation Unit](../entities/Organisation_Unit.md).

<a name="rel__has-person">has-person</a> / [is-the-person-in](../entities/Person.md#user-content-rel__is-the-person-in) : An Affiliation Statement typically has the person, an instance of [Person](../entities/Person.md).

<a name="rel__is-used-in">is-used-in</a> / [has-affiliation-statement](../entities/Activity.md#user-content-rel__has-affiliation-statement) : An Affiliation Statement can be used in any number of [Activities](../entities/Activity.md).

<a name="rel__is-the-result-of">is-the-result-of</a> / [is-the-foundation-for](../entities/Involvement.md#user-content-rel__is-the-foundation-for) : An Affiliation Statement might be a result of an Agent [Involvement](../entities/Involvement.md).
