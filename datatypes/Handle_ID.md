# The Handle (Handle System identifier) datatype

## Definition
The datatype that represents the identifiers of the [Handle System](https://handle.net/).

## Based on
[String](../datatypes/String.md) 

## Restrictions
1. The string consists of the Handle *prefix* (the naming authority) and the *suffix* (the local name), separated by the slash character: `<prefix>/<suffix>`<sup>[1](#fn1)</sup>. The string must match the following regular expression<sup>[1](#fn1)</sup>: `^(?:0\.[^./:]+|[0-9]+(?:\.[0-9]+)*)/.+$` — the *prefix* is a numerically issued naming authority: one or more numeric segments separated by `.` (such as `10872`, `10.1045`, or `20.1000.56.1`), or one of the Handle System reserved namespaces of the form `0.<token>` (such as `0.NA`); the *suffix* is the unique local name<sup>[2](#fn2)</sup>, a non-empty sequence of characters (which may contain further `/`). The URI or URL forms (always carrying a `:` scheme delimiter) are thereby excluded (cf. the next item)
2. It records just the identifier itself, not the URI or URL forms it may occur in (i.e. not `hdl:10872/19464`, `info:hdl/10872/19464`, or `https://hdl.handle.net/10872/19464`)<sup>[2](#fn2)</sup>

## Examples
- `10872/19464`
- `20.1000/100`
- `0.NA/10.5072`

---
## Matches
1. Narrow match of the URI forms a handle may be expressed in: the `hdl:` URI (e.g. `hdl:10872/19464`), the `info:hdl/` URI (e.g. `info:hdl/10872/19464`), and the URL served through the proxy server (e.g. `https://hdl.handle.net/10872/19464`)<sup>[2](#fn2)</sup>

## References
<a name="fn1">\[1\]</a> Sun, Sam; Lannom, Lawrence (2003). *RFC 3651: Handle System Namespace and Resource Definition.* Available from https://www.rfc-editor.org/rfc/rfc3651

<a name="fn2">\[2\]</a> Sun, Sam; Lannom, Lawrence; Boesch, Brian (2003). *RFC 3650: Handle System Overview.* Available from https://www.rfc-editor.org/rfc/rfc3650
