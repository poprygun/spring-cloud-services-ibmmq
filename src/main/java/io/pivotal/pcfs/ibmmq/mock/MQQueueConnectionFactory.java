package io.pivotal.pcfs.ibmmq.mock;


import lombok.Data;

@Data
public class MQQueueConnectionFactory {
    private String host;
    private int port;
    private String queueManager;
    private String userName;

    @Override
    public String toString() {
        return "MQQueueConnectionFactory{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", queueManager='" + queueManager + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
