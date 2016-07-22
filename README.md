# Spring Cloud IBM MQ Service Connector
This library packages IBM MQ Client jars as a cloud connector to be deployed to Pivotal Cloud Foudndry.
MQQueueConnectionFactory will be registered as a bean with the name of user provided service defined name (mq-service) in our example.

### Installation instructions.

1. Build project and deploy to maven repository.
    1. mvn install
    
2. Include dependency to this library to project that need to communicate to IBM MQ.
```
mvn install:install-file -Dpackaging=jar -Dfile=ibmmq-service-broker.jar -DgroupId=io.pivotal.pcfs -DartifactId=spring-cloud-services-ibmmq -Dversion=1.0-SNAPSHOT
```
```
<dependency>
  <groupId>io.pivotal.pcfs</groupId>
  <artifactId>spring-cloud-services-ibmmq</artifactId>
  <version>1.3-SNAPSHOT</version>
</dependency>
```
3. Create a User Provided Service using PCF Client
Service is matched based on uri scheme - it should start with "mq://" 
```bash
cf create-user-provided-service mq-service -p '{"uri":"mq://pivot:letmein@mqhost:1694", "queue_manager":"my-queue-manager"}'
```

### IBM Dependencies.
Following dependencies were downloaded from Websphere installation
```bash
mvn install:install-file -Dpackaging=jar -Dfile=dhbcore.jar -DgroupId=com.ibm -DartifactId=com.ibm.disthub2.dhbcore -Dversion=7.5
mvn install:install-file -Dpackaging=jar -Dfile=com.ibm.mq.jmqi.jar -DgroupId=com.ibm -DartifactId=com.ibm.mq.jmqi -Dversion=7.5
mvn install:install-file -Dpackaging=jar -Dfile=com.ibm.mqjms.jar -DgroupId=com.ibm -DartifactId=com.ibm.mqjms -Dversion=7.5
mvn install:install-file -Dpackaging=jar -Dfile=com.ibm.msg.client.jms.internal.jar -DgroupId=com.ibm -DartifactId=msg.client.jms.internal -Dversion=7.0.1.3
mvn install:install-file -Dpackaging=jar -Dfile=com.ibm.msg.client.jms.jar -DgroupId=com.ibm -DartifactId=msg.client.jms -Dversion=7.0.1.3
mvn install:install-file -Dpackaging=jar -Dfile=com.ibm.msg.client.provider.jar -DgroupId=com.ibm -DartifactId=msg.client.provider -Dversion=7.0.1.3
mvn install:install-file -Dpackaging=jar -Dfile=com.ibm.msg.client.commonservices.jar -DgroupId=com.ibm -DartifactId=msg.client.commonservices -Dversion=7.0.1.3

```