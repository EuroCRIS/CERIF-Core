# Time Based Contents Location

## Definition
A [Contents Location](../datatypes/Contents_Location.md) that is based on a time interval.

## Notes
To be used with documents with [Audio](../entities/Audio_Contents.md) or [Video](../entities/Video_Contents.md) Contents.

## Specialization of
[Contents Location](../datatypes/Contents_Location.md)

## Components
Besides those of Contents Location:
- startTime : [String](../datatypes/Time.md) without a time zone
- endTime : [String](../datatypes/Time.md) without a time zone

## Restrictions
1. If both components are specified, then `startTime < endTime`.

