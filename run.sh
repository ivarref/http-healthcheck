#!/usr/bin/env bash

set -ex

lein jar

java -cp ./target/http-healthcheck-0.1.0-SNAPSHOT.jar no.nsd.HealthCheck http://localhost:3000/healthcheck

echo $?