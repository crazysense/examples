package com.bistel.a3.app;

import com.bistel.a3.platform.factory.engine.PlatformEngine;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kidki on 2016-08-15.
 */
public class Startup {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/platform.xml");

//        KafkaConfig config = new KafkaConfig();
//        config.setBootstrapServers("192.168.0.6:9092");
//        config.setZookeeperConnect("192.168.0.6:2181");
//        config.setConsumerGroup("group1");
//        config.setTopicName("test");

        PlatformEngine factory = applicationContext.getBean(PlatformEngine.class);
//        DataStream<Message> ds = factory.getOrCreateInputDataSource(DataSourceType.kafka, config, Message.class);
//        ds.map(data -> data).print();

        StreamExecutionEnvironment env = (StreamExecutionEnvironment) factory.getEnvironment();
        env.execute();
    }
}
