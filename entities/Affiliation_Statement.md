# Affiliation Statement

## Definition

A statement of a connection between a [Person](../entities/Person.md) and an [Organisation Unit](../entities/Organisation_Unit.md).

## Usage notes

An affiliation expresses the fact that there is some kind of connection. 
Subclasses can be more specific about the type of the connection.

## Attributes

display affiliation: [String](../datatypes/String.md) -- the raw string form of the affiliation as found e.g. in scholarly publications; more structured statements make use of the *institution* and/or *organisation unit* relationships below

display person name: [String](../datatypes/String.md) -- the raw string form of the name of the person as found e.g. in scholarly publications; more structured statements make use of the *person* relationship below

address: [Postal Address](../datatypes/Postal_Address.md)

## Relatioships

<<<<<<< HEAD
An Affiliation Statement can optionally be linked with *institution*, an instance of [Organisation Unit](../entities/Organisation_Unit.md).
=======
<a name="rel35288350-0e23-4190-90f7-9c12fa184007">An Affiliation Statement can be linked with *[contributions](../entities/Contribution_to_Document.md#user-content-rel35288350-0e23-4190-90f7-9c12fa184007)*: an instance of [Contribution to Document](../entities/Contribution_to_Document.md).</a>
>>>>>>> 37635170dbc175126bff1171c68b04f597d82bdc

An Affiliation Statement can optionally be linked with *organisation unit*, an instance of [Organisation Unit](../entities/Organisation_Unit.md).

An Affiliation Statement is typically linked with *person*: an instance of [Person](../entities/Person.md).
