# Page Based Contents Location

## Definition
A [Contents Location](../datatypes/Contents_Location.md) that is based on a range of pages.

## Notes
To be used with paginated documents.

## Specialization of
[Contents Location](../datatypes/Contents_Location.md)

## Components
Besides those of Contents Location:
- startPage : [String](../datatypes/String.md)
- endPage : [String](../datatypes/String.md)

## Restrictions
If both components are specified, then `startPage` must not designate a later page than `endPage` in the paginated document.
