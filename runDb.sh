#!/usr/bin/env bash

docker container run \
   --publish 9092:9082 \
   --detach \
   --name optimizerDb \
   nemerosa/h2