#!/bin/sh

#1. Build project & move .jar to AppContainer directory
dockerDirectory=$(pwd -P)
cd ..
./gradlew clean build
cd Backend/build/libs
cp *.jar $dockerDirectory/AppContainer/app.jar

#2. Run docker containers
docker network create money-net

cd $dockerDirectory/DatabaseContainer
docker build -t db-container .
docker run -p 3307:3306 --name db-container --network money-net -d db-container

cd $dockerDirectory/AppContainer
docker build -t app-container .
docker run -p 9090:8080 --name app-container --network money-net app-container
