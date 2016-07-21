package io.pivotal.pcfs.ibmmq;


import com.ibm.mq.jms.MQQueueConnectionFactory;
import org.springframework.cloud.service.MapServiceConnectionConfigurer;
import org.springframework.cloud.service.MapServiceConnectorConfig;

public class MqConnectionFactoryConfigurer extends MapServiceConnectionConfigurer<MQQueueConnectionFactory, MapServiceConnectorConfig> {
}
