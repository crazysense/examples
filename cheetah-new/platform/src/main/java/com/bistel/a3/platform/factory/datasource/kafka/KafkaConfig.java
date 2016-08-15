package com.bistel.a3.platform.factory.datasource.kafka;

import com.bistel.a3.platform.factory.datasource.DataSourceConfig;

/**
 * Created by kidki on 2016-08-15.
 */
public class KafkaConfig implements DataSourceConfig {
    private String bootstrapServers;
    private String zookeeperConnect;
    private String topicName;
    private String consumerGroup;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getZookeeperConnect() {
        return zookeeperConnect;
    }

    public void setZookeeperConnect(String zookeeperConnect) {
        this.zookeeperConnect = zookeeperConnect;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }
}
