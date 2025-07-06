# Time

## Definition
Represents time of day, with an optional time zone, in line with the ISO 8601:2019 standard.<sup>[1](#fn1)</sup>

## Notes
The time is represented as `hh:mm:ss[.ffffff][(Z|(+|-)zh:zm)]` where
- _hh_ is the hour (0–23), two digits (left padded with 0)
- _mm_ is the minute (0–59), two digits (left padded with 0)
- _ss_ is the second (0–59), two digits (left padded with 0)
- _ffffff_ is the optional fractional part: it may not end in 0
- the rest is the optional specification of a time zone:
  - _zh_ is the hour part of the time zone specification, two digits (left padded with 0)
  - _zm_ is the minute part of the time zone specification, two digits (left padded with 0)
  - `Z` stands for the Coordinated Universal Time (UTC), which can also be expressed as `+00:00` or `-00:00`

## Examples
- 09:25:00+02:00 denotes 9:25am in the Central European Saving Time zone
- 23:30:00 denotes 11:30pm (in a time zone that is implied by the context)

---
## Matches
Closely matches `xsd:time`.<sup>[2](#fn2)</sup>

## References
<a name="fn1">\[1\]</a> *ISO 8601-1:2019(en). Date and time — Representations for information interchange. Part 1: Basic rules*. International Standards Organization. 

<a name="fn2">\[2\]</a> *XML Schema Part 2: Datatypes Second Edition.* W3C Recommendation 28 October 2004. Paul V. Biron, Ashok Malhotra (eds.). Available from https://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#time
