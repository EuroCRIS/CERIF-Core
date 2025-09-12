#!/usr/bin/env bash

D="$(dirname "$0")"
(
	cd "$D"/owl
	mvn clean package
) && \
java -jar "$D"/owl/target/cerif2-owl-tools-*.jar .
