#!/bin/sh

#Remove app.jar
dockerDirectory=$(pwd -P)
cd $dockerDirectory/AppContainer
rm -f app.jar

#Remove docker containers with pattern: *-container
docker container rm $(docker ps -a -q --filter name=-container) -f

#Remove docker images with pattern: *-container
docker image rm db-container
docker image rm app-container
