docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
./gradlew build
docker build --build-arg JAR_FILE=build/libs/web-0.0.1-SNAPSHOT.jar -t gs-spring-boot-docker .
docker run -p 8080:8080 gs-spring-boot-docker

kubectl apply -f bb.yaml