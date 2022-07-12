# Affiliation Statement

## Definition

A statement of a connection between a [Person](../entities/Person.md) and an [Organisation Unit](../entities/Organisation_Unit.md).

## Usage notes

An affiliation statement expresses the fact that there is some kind of connection. 
Subclasses can be more specific about the type of the connection.

Affiliation statements can have various levels of specificity and structure.
They can exist as standalone objects (typically where an institution makes statements about its staff) or in connection to a specific [Activity](../entities/Activity.md) where it allows to specify the affiliations or representations to be specified.

## Attributes

display affiliation: [String](../datatypes/String.md) -- the raw string form of the affiliation as found e.g. in scholarly publications; more structured statements make use of the *institution* and/or *organisation unit* relationships below

display person name: [String](../datatypes/String.md) -- the raw string form of the name of the person as found e.g. in scholarly publications; more structured statements make use of the *person* relationship below

address: [Postal Address](../datatypes/Postal_Address.md)

contacts: List<[URI](../datatypes/URI.md)>


## Relationships

An Affiliation Statement can optionally be linked with *institution*, an instance of [Organisation Unit](../entities/Organisation_Unit.md).

An Affiliation Statement can optionally be linked with *organisation unit*, an instance of [Organisation Unit](../entities/Organisation_Unit.md).

An Affiliation Statement is typically linked with *person*: an instance of [Person](../entities/Person.md).

<a name="rel__is-used-in">An Affiliation Statement can optionally *[be used in](../entities/Activity.md#user-content-rel__has-affiliation-statements)* any number of [Activities](../entities/Activity.md).</a>
