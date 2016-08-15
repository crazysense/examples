package com.bistel.a3.platform.factory.engine.flink.datasource.kafka;

import com.bistel.a3.platform.factory.datasource.DataSource;
import com.bistel.a3.platform.factory.datasource.DataSourceConfig;
import com.bistel.a3.platform.factory.datasource.kafka.KafkaConfig;
import com.bistel.a3.platform.factory.engine.flink.datasource.serializer.JsonDeserializer;
import com.bistel.a3.platform.factory.engine.flink.datasource.serializer.JsonSerializer;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer08;

import java.util.Properties;

/**
 * Created by kidki on 2016-08-15.
 */
public class FlinkKafkaDataSource implements DataSource {
    @Override
    public SourceFunction<?> createInputDataSource(DataSourceConfig config, Class<?> clazz) {
        // Check Config
        if (config.getClass() != KafkaConfig.class) {
            throw new IllegalArgumentException("Datasource configuration support only Kafka");
        }

        KafkaConfig kafkaConfig = (KafkaConfig) config;

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", kafkaConfig.getBootstrapServers());
        props.setProperty("zookeeper.connect", kafkaConfig.getZookeeperConnect());
        props.setProperty("group.id", kafkaConfig.getConsumerGroup());

        FlinkKafkaConsumer08<?> consumer = new FlinkKafkaConsumer08<>(kafkaConfig.getTopicName(), new JsonDeserializer<>(clazz), props);
        return consumer;
    }

    @Override
    public SinkFunction<?> createOutputDataSource(DataSourceConfig config, Class<?> clazz) {
        // Check Config
        if (config.getClass() != KafkaConfig.class) {
            throw new IllegalArgumentException("Datasource configuration support only Kafka");
        }

        KafkaConfig kafkaConfig = (KafkaConfig) config;

        FlinkKafkaProducer08<?> producer = new FlinkKafkaProducer08<>(kafkaConfig.getBootstrapServers(), kafkaConfig.getTopicName(), new JsonSerializer<>(clazz));
        return producer;
    }
}
