# Date Range

## Definition
A range of [Dates](../datatypes/Date.md).

## Components
start date: [Date](../datatypes/Date.md) — optional; if missing, an indeterminate time in the past is implied

start date inclusive: [Boolean](../datatypes/Boolean.md) — flags whether the *start date* belongs to the range or not

end date: [Date](../datatypes/Date.md) — optional; if missing, "until present" is implied

end date inclusive: [Boolean](../datatypes/Boolean.md) — flags whether the *end date* belongs to the range or not

## Constraints
The *start date* and *end date* must have the same granularity.

---
## Matches
In the case of day granularity, this is a close match of the [`daterange` datatype of PostgreSQL](https://www.postgresql.org/docs/current/rangetypes.html), except for the `empty` range.

Narrow match of the [VIVO DateTimeInterval](https://wiki.lyrasis.org/display/VIVODOC112x/DateTimeValue+and+DateTimeInterval+Models).
