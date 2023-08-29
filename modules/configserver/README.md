# Demo 1: template-service

## Environment
- docker
- docker-compose
- mvn
- Java

## Account
- Docker hub
## Build & Start project
### Stage 1 - Development with local postgres container
```shell
# build
mvn package spring-boot:repackage -DskipTests
# containerize & start service
docker-compose -f docker-compose.yml up --force-recreate --no-deps --build

# Clean stage:
docker compose -f docker-compose.yml down
```
## Test
## Test for 2 local stages
```
http://localhost:8071/config/eureka-server.yml
