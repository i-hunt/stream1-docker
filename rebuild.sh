#!/bin/sh
set -eux
pushd AccountService
mvn clean package -DskipTests
popd
docker-compose down && docker-compose up -d --build
