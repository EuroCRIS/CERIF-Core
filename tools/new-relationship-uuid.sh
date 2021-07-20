#!/bin/bash
echo rel$( uuidgen | tr A-F a-f ) | sed -e 's/\(.*\)/<a name="\1"> ... [xxx](..\/entities\/XXX.md#user-content-\1) ... <\/a>/'