# Agent

## Definition
A [Person](../entities/Person.md) or [Organisation Unit](../entities/Organisation_Unit.md). 

## Usage notes
It is an abstract entity representing the root ancestor for Person and Organisation unit. All relatioships which might be linked with Person and/or Organisation unit, should be generalized by linking the Agent entity. 

## Generalization of
[Person](../entities/Person.md)

[Organisation Unit](../entities/Organisation_Unit.md)

## Attributes
contributorships: List<[Contributorship](../entities/Contributorship.md)> 
* representing structured information about linking an agent with [documents](../entities/Document.md)  

## Matches
1. Close match of [FOAF Agent](http://xmlns.com/foaf/spec/#term_Agent) 

## References
