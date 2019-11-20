#!/bin/sh
cd employee-portal
mvn package
cd ..
cd docker
docker-machine create --virtualbox-memory "4096" --driver virtualbox ep
eval "$(docker-machine env ep)"
docker-compose build
