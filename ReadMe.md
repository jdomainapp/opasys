# OPASys
## 1. Download and Build projects
### JPA project
- Download JPA project from https://github.com/jdomainapp/jda.git
- Copy library folder `local-maven-repo` to local Maven repository
- Rename JAR Maven lib file `xml1.1.jar` in local Maven repository to `xml1.8.jar`
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
- Run by commandline
```
cd ../courseman/msa/modules/configserver
mvn spring-boot:run
```
- or Run by class `org.jda.example.coursemanmsa.configserver.ConfigurationServerApplication`
### Run Discovery Service
- Description:  Discovery service like Eureka will abstract away the physical location of our services. Eureka can seamlessly add and remove service
instances from an environment without impacting the service clients. We use Eureka to look up a service.
- Run by commandline
```
cd ../courseman/msa/modules/eurekaserver
mvn spring-boot:run
```
- or Run by class `org.jda.example.coursemanmsa.eurekaserver.EurekaServerApplication`
### Run Gateway Service
- Description: A service gateway acts as an intermediary between the service client and an invoked service, pulls apart the path coming in from the service client call and determines what service the service client is trying to invoke. It is a central point to apply rules, policies for requests and responses from/to servcies. The Spring Cloud Gateway allows us to implement custom business logic through filters (pre- and post-filters), it integrates with Netflix’s Eureka Server and can automatically map services registered with Eureka to a route. 
- Run by commandline
```
cd ../courseman/msa/modules/gatewayserver
mvn spring-boot:run
```
- or Run by class `org.jda.example.coursemanmsa.gatewayserver.ApiGatewayServerApplication`
## 5. Run OPASys services
- Access the directory of each service, run the command: `mvn spring-boot:run`
### project-service
### opa-service
### org-asset-service
- Manage forms and templates related to procedures and policies in the organization.
- When procedures and policies are changed, users will create/update/delete org-assets on this service. Then, the service will send kafka events to `opa-service' to update local org-assets in `opa-service`.
### knowledge-asset-service
### risk-service
### defect-service
### issue-service
### security-service
- Manage users of the system.
- When user is changed (add/edit/delete), `security-service` sends kafka event to `project-service` and `issue-service` to update their local users.
