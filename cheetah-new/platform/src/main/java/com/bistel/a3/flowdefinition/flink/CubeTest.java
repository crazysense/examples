package com.bistel.a3.flowdefinition.flink;

import com.bistel.a3.common.data.Message;
import com.bistel.a3.cube.TestClass;
import com.bistel.a3.platform.factory.datasource.DataSourceType;
import com.bistel.a3.platform.factory.datasource.kafka.KafkaConfig;
import com.bistel.a3.platform.factory.engine.PlatformEngine;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
public class CubeTest implements Serializable {

    @Autowired
    private PlatformEngine platform;

    @Bean
    public SourceFunction<Message> inputSource() {
        KafkaConfig consumerConfig = new KafkaConfig();
        consumerConfig.setBootstrapServers("192.168.0.6:9092");
        consumerConfig.setZookeeperConnect("192.168.0.6:2181");
        consumerConfig.setConsumerGroup("group1");
        consumerConfig.setTopicName("test");

        return platform.getOrCreateInputDataSource(DataSourceType.kafka, consumerConfig, Message.class);
    }

    @Bean
    public SinkFunction<Message> outputSource() {
        KafkaConfig producerConfig = new KafkaConfig();
        producerConfig.setBootstrapServers("192.168.0.6:9092");
        producerConfig.setTopicName("test1");

        return platform.getOrCreateOutputDataSource(DataSourceType.kafka, producerConfig, Message.class);
    }

    @Bean
    @Autowired
    public DataStreamSink<Message> parsing(@Qualifier("inputSource") SourceFunction<Message> inputSource,
                                           @Qualifier("outputSource") SinkFunction<Message> outputSource,
                                           TestClass testClass) {
        StreamExecutionEnvironment env = (StreamExecutionEnvironment) platform.getEnvironment();
        return env.addSource(inputSource)
                .map(message -> testClass.func1(message))
                .addSink(outputSource);
    }

    @Bean
    public SourceFunction<Message> inputSource2() {
        KafkaConfig consumerConfig = new KafkaConfig();
        consumerConfig.setBootstrapServers("192.168.0.6:9092");
        consumerConfig.setZookeeperConnect("192.168.0.6:2181");
        consumerConfig.setConsumerGroup("group1");
        consumerConfig.setTopicName("test1");

        return platform.getOrCreateInputDataSource(DataSourceType.kafka, consumerConfig, Message.class);
    }

    @Bean
    @Autowired
    public DataStream<Message> save(@Qualifier("inputSource2") SourceFunction<Message> inputSource,
                                    TestClass testClass) {
        StreamExecutionEnvironment env = (StreamExecutionEnvironment) platform.getEnvironment();
        return env.addSource(inputSource)
                .map(message -> testClass.func2(message));
    }
}
