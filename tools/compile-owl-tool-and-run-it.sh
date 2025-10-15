#!/usr/bin/env bash

D="$(dirname "$0")"
[ "$1" == "--no-compile" ] || \
(
	cd "$D"/owl
	mvn clean package
) && \
java -jar "$D"/owl/target/cerif2-owl-tools-*.jar .
