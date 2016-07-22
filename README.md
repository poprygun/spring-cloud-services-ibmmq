# Spring Cloud IBM MQ Service Connector

This library packages IBM MQ Client jars as a cloud connector to be deployed to Pivotal Cloud Foudndry.
MQQueueConnectionFactory will be registered as a bean with the name of user provided service defined name (mq-service) in our example.

### Installation instructions.

1. Build project and deploy to maven repository.
    1. mvn install
    
2. Include dependency to this library to project that need to communicate to IBM MQ.
```
<dependency>
  <groupId>io.pivotal.pcfs</groupId>
  <artifactId>spring-cloud-services-ibmmq</artifactId>
  <version>1.3-SNAPSHOT</version>
</dependency>
```
3. Create a User Provided Service using PCF Client
```bash
cf create-user-provided-service mq-service -p '{"uri":"mq://pivot:letmein@mqhost:1694", "queue_manager":"my-queue-manager"}'
```