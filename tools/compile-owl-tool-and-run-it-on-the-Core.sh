#!/usr/bin/env bash

(
	cd tools/owl
	mvn clean package
) && \
java -jar tools/owl/target/cerif2-owl-tools-*.jar .
