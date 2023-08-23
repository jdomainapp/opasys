OPASys
-------------

## 1. Download and Build projects
### JPA project
- Download JPA project from https://github.com/jdomainapp/jda.git
- Copy library folder `local-maven-repo` to local Maven repository
- Copy JAR Maven lib file `com/thoughtworks/xstream/xstream/1.4.18/xstream-1.4.18.jar` in local Maven repository to folder `com/thoughtworks/xstream/xstream/1.4.11.1` and rename to xstream-1.4.11.1.jar
- Run maven complie and install 
```
cd ../jpa
mvn compile
mvn clean install -DskipTests
```
### OPASys project
- Download JPA project from https://github.com/jdomainapp/opasys.git
- Run maven complie and install 
```
cd ../jpa
mvn compile
mvn clean install -DskipTests
```
## 2. Create Postgresql database
- Create a postgresql database `opasys` with user/password: admin/password
- Run `db_create.sql` in folder `opaysys/src/main/resources`
## 3. Start Apache Kafka and register topics 
```
# Start the ZooKeeper service
bin/zookeeper-server-start.sh config/zookeeper.properties
```
```
# Start the Kafka broker service
bin/kafka-server-start.sh config/server.properties
```

```
# create topics (only run in first time)
bin/kafka-topics.sh --create --partitions 1 --replication-factor 1 --topic userChangeTopic --bootstrap-server localhost:9092
bin/kafka-topics.sh --create --partitions 1 --replication-factor 1 --topic orgAssetChangeTopic --bootstrap-server localhost:9092
```
## 4. Run common services in order
### Run Config service
- Description: The Spring Cloud Configuration Server (aka Config Server) allows you to set
up application properties with environment-specific values. The other services will retrieve its properties from Config Server when the services lanch.
- Run by class `org.jda.example.coursemanmsa.configserver.ConfigurationServerApplication`
### Run Discovery Service
- Description:  Discovery service like Eureka will abstract away the physical location of our services. Eureka can seamlessly add and remove service
instances from an environment without impacting the service clients. We use Eureka to look up a service.
- Run by class `org.jda.example.coursemanmsa.eurekaserver.EurekaServerApplication`
### Run Gateway Service
- Description: A service gateway acts as an intermediary between the service client and an invoked service, pulls apart the path coming in from the service client call and determines what service the service client is trying to invoke. It is a central point to apply rules, policies for requests and responses from/to servcies. The Spring Cloud Gateway allows us to implement custom business logic through filters (pre- and post-filters), it integrates with Netflix’s Eureka Server and can automatically map services registered with Eureka to a route. 
- Run by class `org.jda.example.coursemanmsa.gatewayserver.ApiGatewayServerApplication`
## 5. Run OPASys services
- Access the directory of each service, run the command: `mvn spring-boot:run`
### project-service
- Manage project information including Project, Activity, Risk, Defect, Issue, User. Activity in `project-service` will be assets in `knowledge asset' when project will be completed. 
- When a project is created, the user want to get data from the similar completed project, `project-servie` uses `opa module` to send requests to `opa-service`. Then, `opa-service` forward request to `risk-service`, `knowledge-asset-service`, `defect-service`, `issue-service`. The `project-service` also gets the forms and templates of the organization by sending a request to the `opa-service`. Similarly, `opa-service` sends request to `org-asset-service`
- When a project is completed, `project-servie` uses `opa module` to send `onComplete event` with all data of the project to `opa-service`. Then, `opa-service` forward data to `risk-service`, `knowledge-asset-service`, `defect-service`, `issue-service`.
### opa-service
- is an intermediary to exchange information between the `project-service` and the `asset-services`
### org-asset-service
- Manage forms and templates related to procedures and policies in the organization.
- When procedures and policies are changed, users will create/update/delete org-assets on this service. Then, the service will send kafka events to `opa-service' to update local org-assets in `opa-service`.
### knowledge-asset-service
- Manage information about Plan, Config, Metric and Finance of a completed project.
- This service exchanges information with `opa-service`.
### risk-service
- Manage information about Risk of a completed project.
- This service exchanges information with `opa-service`.
### defect-service
- Manage information about Defect of a completed project.
- This service exchanges information with `opa-service`.
### issue-service
- Manage information about Issue of a completed project including Isuse, Assignee, Comments.
- This service exchanges information with `opa-service`.
### security-service
- Manage users of the system.
- When user is changed (add/edit/delete), `security-service` sends kafka event to `project-service` and `issue-service` to update their local users.

# Running OPASys using Docker Engine

## Running infrastructure services in a Docker container

1. Create a Docker container to run the services

```
docker run --network host --name infra-services -it ducmle/jdare:latest bash
```

2. Copy .jar files of the infrastructure services to the `/jda` folder in the container
   Use the `docker cp` command on the host machine. For example:
```
CONFIG_SERVER=~/short/opasys/modules/configserver
EUREKA_SERVER=~/short/opasys/modules/eurekaserver
GW_SERVER=~/short/opasys/modules/gatewayserver

docker cp $CONFIG_SERVER/target/configserver-0.0.1-SNAPSHOT.jar infra-services:/jda/configserver.jar

docker cp $EUREKA_SERVER/target/eurekaserver-0.0.1-SNAPSHOT.jar infra-services:/jda/eurekaserver.jar

docker cp $GW_SERVER/target/gatewayserver-0.0.1-SNAPSHOT.jar infra-services:/jda/gatewayserver.jar
```

3. Execute the jar files in this order: config-server => discovery server => gateway server
   - From the shell of the Docker container, run the application `.jar` file, using the `java -jar` command.

```
java -jar /jda/configserver.jar

java -jar /jda/eurekaserver.jar

java -jar /jda/gatewayserver.jar
```

## Running each service in a separate Docker container

1. Create a Docker container to run the service

Suppose the service name is `address`, whose server port is 8082 and we wish to expose it to the public:

```
docker run --add-host=inf-server:host-gateway -p 8082:8082 --name address -it ducmle/jdare:latest bash
```

2. Update the service configuration file to use `inf-server` instead of `localhost`:
   - Change in the `.properties` file of the service in the `/config` folder of the config server
   - Change in the the `bootstrap.yml`
   
3. Copy .jar files of the infrastructure services to the `/jda` folder in the container
   Use the `docker cp` command on the host machine. For example:
```
docker cp $ADDRESS/target/address-service-0.0.1-SNAPSHOT.jar address:/jda/address-service.jar
```

4. Execute the jar file from the shell of the Docker container using the `java -jar` command.

```
java -jar /jda/address-service.jar
```

## Running multiple instances of a service in different Docker containers

Similar to running a single instance in a Docker container (discussed in the previous section), except that **step 1** requires that, for each instance, the service port is mapped to a different host port. 

The following commands create two containers running two instances of the service `address`. The two containers are named `address1`, `address2` and their host ports are 7071 and 7072 (respectively).

```
docker run --add-host=inf-server:host-gateway -p 7071:8082 --name address1 -it ducmle/jdare:latest bash

docker run --add-host=inf-server:host-gateway -p 7072:8082 --name address2 -it ducmle/jdare:latest bash

```

### Note:
1. If you get the `java.net.UnknownHostException: <some-host-name>` when service is connecting to the Kafka server then you need to add `<some-host-name>` to the `hosts` file of the container OS
