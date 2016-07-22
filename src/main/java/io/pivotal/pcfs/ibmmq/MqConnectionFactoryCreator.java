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
    private static Logger logger = Logger.getLogger(MqServiceInfoCreator.class.getName());



    @Override
    public MQQueueConnectionFactory create(MqServiceInfo serviceInfo, ServiceConnectorConfig serviceConnectorConfiguration) {
        MQQueueConnectionFactory connectionFactory = createMqConnectionFactory(serviceInfo);
        return connectionFactory;
    }

    private MQQueueConnectionFactory createMqConnectionFactory(MqServiceInfo serviceInfo) {
        //todo - follow http://www.massapi.com/class/mq/MQQueueConnectionFactory.html
//        try {
            MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();
            logger.info("****Creating MQ Connection Factory:");
            logger.info("****Host: " + serviceInfo.getHost());
            logger.info("****Port: " + serviceInfo.getPort());
            logger.info("****Queue Manager: " + serviceInfo.getQueueManager());
            logger.info("****User: " + serviceInfo.getUserName());

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
