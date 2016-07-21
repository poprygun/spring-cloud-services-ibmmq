package io.pivotal.pcfs.ibmmq;


import org.springframework.cloud.service.UriBasedServiceInfo;
import org.springframework.cloud.util.UriInfo;

import java.util.List;

public class MqServiceInfo extends UriBasedServiceInfo {

    public static final String MQ_SCHEME = "mq";
    public static final String MQS_SCHEME = "mqs";

    private String queueManager;
    private List<String> uris;

    public MqServiceInfo(String id, String host, int port, String user, String pass, String path, String queueManager) {
        super(id, MQ_SCHEME, host, port, user, pass, path);
        this.queueManager = queueManager;
    }

    public MqServiceInfo(String id, String host, int port, String user, String pass, String path, String queueManager, List<String> uris) {
        super(id, MQ_SCHEME, host, port, user, pass, path);
        this.uris = uris;
        this.queueManager = queueManager;
    }

    @ServiceProperty(category="connection")
    public String getQueueManager() {
        return queueManager;
    }

    @ServiceProperty(category="connection")
    public List<String> getUris() {
        return uris;
    }

    @Override
    protected UriInfo validateAndCleanUriInfo(UriInfo uriInfo) {
        if (uriInfo.getScheme() == null) {
            throw new IllegalArgumentException("Missing scheme in mq URI: " + uriInfo);
        }

        if (uriInfo.getHost() == null) {
            throw new IllegalArgumentException("Missing authority in mq URI: " + uriInfo);
        }

        String path = uriInfo.getPath();
        if (path != null) {
            if (path.indexOf('/') != -1) {
                throw new IllegalArgumentException("Multiple segments in path of mq URI: " + uriInfo);
            }
        }

        return uriInfo;
    }
}
