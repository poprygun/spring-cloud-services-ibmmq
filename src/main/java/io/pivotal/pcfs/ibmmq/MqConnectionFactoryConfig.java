package io.pivotal.pcfs.ibmmq;


import org.springframework.cloud.service.MapServiceConnectorConfig;

import java.util.Map;

public class MqConnectionFactoryConfig extends MapServiceConnectorConfig {
    private Integer channelCacheSize;

    public MqConnectionFactoryConfig(Map<String, Object> properties) {
        this(properties, null);
    }

    public MqConnectionFactoryConfig(Integer channelCacheSize) {
        this(null, channelCacheSize);
    }

    public MqConnectionFactoryConfig(Map<String, Object> properties, Integer channelCacheSize) {
        super(properties);
        this.channelCacheSize = channelCacheSize;
    }

    public Integer getChannelCacheSize() {
        return channelCacheSize;
    }
}
