package io.pivotal.pcfs.ibmmq;


import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MqServiceInfoCreator extends CloudFoundryServiceInfoCreator<MqServiceInfo> {
    public MqServiceInfoCreator() {
        super(new Tags(), MqServiceInfo.MQ_SCHEME);
    }

    private static Logger logger = Logger.getLogger(MqServiceInfoCreator.class.getName());

    @SuppressWarnings("unchecked")
    public MqServiceInfo createServiceInfo(Map<String, Object> serviceData) {
        Map<String, Object> credentials = getCredentials(serviceData);

        String id = getId(serviceData);

        String uri = getUriFromCredentials(credentials);
        String queueManager = getStringFromCredentials(credentials, "queue_manager");

        String user = parseStringBetween(uri, "//", ":");
        String pass = parseStringBetween(uri, ":", "@");
        String host = parseStringBetween(uri, "@", ":");
        int port = Integer.parseInt(uri.substring(uri.lastIndexOf(":") + 1));

        if (credentials.containsKey("uris")) {
            List<String> uris = (List<String>) credentials.get("uris");
            return new MqServiceInfo(id, host, port, user, pass, null, queueManager, uris);
        }

        return new MqServiceInfo(id, host, port, user, pass, null, queueManager);
    }

    private String parseStringBetween(String toParse, String start, String end) {
        Pattern p = Pattern.compile(Pattern.quote(start) + "(.*?)" + Pattern.quote(end));
        Matcher m = p.matcher(toParse);
        while (m.find()) {
            return m.group(1);
        }
        //todo fix flow here
        return null;
    }
}
