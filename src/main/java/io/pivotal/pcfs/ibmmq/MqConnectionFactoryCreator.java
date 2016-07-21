package io.pivotal.pcfs.ibmmq;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

import javax.jms.JMSException;

public class MqConnectionFactoryCreator extends AbstractServiceConnectorCreator<MQQueueConnectionFactory, MqServiceInfo> {
    private MqConnectionFactoryConfigurer configurer = new MqConnectionFactoryConfigurer();

    @Override
    public MQQueueConnectionFactory create(MqServiceInfo serviceInfo, ServiceConnectorConfig serviceConnectorConfiguration) {
        MQQueueConnectionFactory connectionFactory = createMqConnectionFactory(serviceInfo);

        configurer.configure(connectionFactory, (MqConnectionFactoryConfig) serviceConnectorConfiguration);

        return connectionFactory;
    }

    private MQQueueConnectionFactory createMqConnectionFactory(MqServiceInfo serviceInfo) {
        MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();

        connectionFactory.setHostName(serviceInfo.getHost());
        try {
            connectionFactory.setPort(serviceInfo.getPort());
            connectionFactory.setQueueManager(serviceInfo.getQueueManager());

            return connectionFactory;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;
    }

}
