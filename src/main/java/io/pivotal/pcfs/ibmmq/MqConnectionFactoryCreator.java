package io.pivotal.pcfs.ibmmq;

import com.ibm.mq.jms.JMSC;
//import com.ibm.mq.jms.MQQueueConnectionFactory;
import io.pivotal.pcfs.ibmmq.mock.MQQueueConnectionFactory;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;

import javax.jms.JMSException;
import java.util.logging.Logger;

public class MqConnectionFactoryCreator extends AbstractServiceConnectorCreator<MQQueueConnectionFactory, MqServiceInfo> {
    private MqConnectionFactoryConfigurer configurer = new MqConnectionFactoryConfigurer();
    private static Logger logger = Logger.getLogger(MqConnectionFactoryCreator.class.getName());


    @Override
    public MQQueueConnectionFactory create(MqServiceInfo serviceInfo, ServiceConnectorConfig serviceConnectorConfiguration) {
        MQQueueConnectionFactory connectionFactory = createMqConnectionFactory(serviceInfo);
        return connectionFactory;
    }

    private MQQueueConnectionFactory createMqConnectionFactory(MqServiceInfo serviceInfo) {
        //todo - follow http://www.massapi.com/class/mq/MQQueueConnectionFactory.html
//        try {
        MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
        connectionFactory.setHost(serviceInfo.getHost());
        connectionFactory.setPort(serviceInfo.getPort());
        connectionFactory.setQueueManager(serviceInfo.getQueueManager());
        connectionFactory.setUserName(serviceInfo.getUserName());
        logger.info("****Creating MQ Connection Factory: " + connectionFactory);


//            connectionFactory.setHostName(serviceInfo.getHost());
//            connectionFactory.setPort(serviceInfo.getPort());
//            connectionFactory.setQueueManager(serviceInfo.getQueueManager());
//
//            connectionFactory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
//            connectionFactory.createQueueConnection().start();

        return connectionFactory;
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

}
