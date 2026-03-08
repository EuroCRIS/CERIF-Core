#!/usr/bin/env bash

# This script publishes locally generated artifacts to the server.
# It assumes a ssh connection can be established.
# It does nothing on branches other than "main".

SERVER_HOSTNAME=cerif2@cerif2.eu

if [ "$(basename $( pwd ) )" != "CERIF-Core" ] ; then
  echo >/dev/stderr "This script is to be run in the main directory of the CERIF-Core project"
  exit 1
fi

if [ "$(git branch --show-current)" == "main" ] ; then
  rsync -v -hhh --ignore-times --delay-updates serializations/RDF/core.* serializations/RDF/per-class/* $SERVER_HOSTNAME:/var/www/cerif2.eu/
else
  echo >>/dev/stderr "Not on the main branch, not deploying, but attempting a connection"
  ssh $SERVER_HOSTNAME echo 'Hello, world!'
fi
