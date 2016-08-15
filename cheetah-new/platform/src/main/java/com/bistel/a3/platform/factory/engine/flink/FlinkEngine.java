package com.bistel.a3.platform.factory.engine.flink;

import com.bistel.a3.platform.factory.datasource.DataSource;
import com.bistel.a3.platform.factory.datasource.DataSourceConfig;
import com.bistel.a3.platform.factory.datasource.DataSourceType;
import com.bistel.a3.platform.factory.engine.PlatformEngine;
import com.bistel.a3.platform.factory.engine.flink.datasource.kafka.FlinkKafkaDataSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * Created by kidki on 2016-08-15.
 */
public class FlinkEngine extends PlatformEngine {

    private static StreamExecutionEnvironment envInstance;

    @Override
    public synchronized StreamExecutionEnvironment getEnvironment() {
        if (envInstance == null) {
            envInstance = StreamExecutionEnvironment.getExecutionEnvironment();
        }
        return envInstance;
    }

    @Override
    protected DataSource createDataSource(DataSourceType dataSourceType) {
        DataSource dataSource = null;
        switch (dataSourceType) {
            case kafka:
                dataSource = new FlinkKafkaDataSource();
                break;
            case rabbitmq:
                break;
            case unknown:
            default:
                break;
        }
        return dataSource;
    }

    @Override
    protected SourceFunction<?> createInputDataSource(DataSource dataSource, DataSourceConfig config, Class<?> clazz) {
        return (SourceFunction<?>) dataSource.createInputDataSource(config, clazz);
    }

    @Override
    protected SinkFunction<?> createOutputDataSource(DataSource dataSource, DataSourceConfig config, Class<?> clazz) {
        return (SinkFunction<?>) dataSource.createOutputDataSource(config, clazz);
    }
}
