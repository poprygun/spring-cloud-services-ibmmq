package org.springframework.cloud.cloudfoundry;


import io.pivotal.pcfs.ibmmq.MqServiceInfo;
import io.pivotal.pcfs.ibmmq.MqServiceInfoCreator;
import org.junit.Test;
import org.springframework.cloud.service.ServiceInfo;

import java.util.List;
import java.util.ServiceLoader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CloudFoundryConnectorMqServiceTest extends AbstractCloudFoundryConnectorTest {
    protected static final String hostname2 = "11.21.31.41";
    private String queueManager = "qManager-1";
    private String userName = "user123";
    private String pwd = "pwd123";

    @Test
    public void loadService() throws Exception {
        ServiceLoader serviceLoader =
                ServiceLoader.load(MqServiceInfoCreator.class);
        for (Object cpService : serviceLoader) {
            cpService.toString();
        }
    }

    @Test
    public void mqServiceCreationMultipleUris() {
        when(mockEnvironment.getEnvValue("VCAP_SERVICES"))
                .thenReturn(getServicesPayload(
                        getMqServicePayloadMultipleUris("mq-1", hostname, hostname2
                                , port, port2, userName, pwd, queueManager),
                        getMqServicePayloadMultipleUris("mq-2", hostname, hostname2
                                , port, port2, userName, pwd, queueManager)));

        List<ServiceInfo> serviceInfos = testCloudConnector.getServiceInfos();
        assertServiceFoundOfType(serviceInfos, "mq-1", ServiceInfo.class);
        assertServiceFoundOfType(serviceInfos, "mq-2", ServiceInfo.class);

        MqServiceInfo MqServiceInfo = (MqServiceInfo) serviceInfos.get(0);

        assertNotNull(MqServiceInfo.getUserName());
        assertTrue(MqServiceInfo.getUserName().contains(userName));

        assertNotNull(MqServiceInfo.getPassword());
        assertTrue(MqServiceInfo.getPassword().contains(pwd));

        assertNotNull(MqServiceInfo.getPort());
        assertEquals(port, MqServiceInfo.getPort());

        assertNotNull(MqServiceInfo.getUri());
        assertTrue(MqServiceInfo.getUri().contains(hostname));

        assertNotNull(MqServiceInfo.getUris());
        assertEquals(2, MqServiceInfo.getUris().size());
        assertTrue(MqServiceInfo.getUris().get(0).contains(hostname));
        assertTrue(MqServiceInfo.getUris().get(0).contains(String.valueOf(port)));
        assertTrue(MqServiceInfo.getUris().get(1).contains(hostname2));
        assertTrue(MqServiceInfo.getUris().get(1).contains(String.valueOf(port2)));

        assertNotNull(MqServiceInfo.getQueueManager());
        assertTrue(MqServiceInfo.getQueueManager().contains(queueManager));
    }

    private String getMqServicePayloadMultipleUris(String serviceName, String hostname, String hostname2
            , int port, int port2, String userName, String pwd
            , String queueManager) {

        String payload = readTestDataFile("test-mq-info-multiple-uris.json");
        payload = payload.replace("$serviceName", serviceName);
        payload = payload.replace("$user", userName);
        payload = payload.replace("$password", pwd);
        payload = payload.replace("$hostname1", hostname);
        payload = payload.replace("$hostname2", hostname2);
        payload = payload.replace("$port1", Integer.toString(port));
        payload = payload.replace("$port2", Integer.toString(port2));
        payload = payload.replace("$queueManager", queueManager);

        return payload;
    }
}
