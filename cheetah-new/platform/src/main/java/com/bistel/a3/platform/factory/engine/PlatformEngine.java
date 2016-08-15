package com.bistel.a3.platform.factory.engine;

import com.bistel.a3.platform.factory.datasource.DataSource;
import com.bistel.a3.platform.factory.datasource.DataSourceConfig;
import com.bistel.a3.platform.factory.datasource.DataSourceType;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by myyuk on 2016-08-15.
 */
public abstract class PlatformEngine implements Serializable {
    private Map<String, DataSource> inputDataSourceProxy = new ConcurrentHashMap<>();
    private Map<String, DataSource> outputDataSourceProxy = new ConcurrentHashMap<>();

    public <T> T getOrCreateInputDataSource(DataSourceType inputType, DataSourceConfig config, Class<?> clazz) {
        String key = String.format("%s_%s_%s", inputType.name(), config.toString(), clazz.getName());
        DataSource ds = inputDataSourceProxy.get(key);
        if (ds == null) {
            ds = createDataSource(inputType);
            inputDataSourceProxy.put(key, ds);
        }
        return (T) ds.createInputDataSource(config, clazz);
    }

    public <T> T getOrCreateOutputDataSource(DataSourceType outputType, DataSourceConfig config, Class<?> clazz){
        String key = String.format("%s_%s_%s", outputType.name(), config.toString(), clazz.getName());
        DataSource ds = outputDataSourceProxy.get(key);
        if (ds == null) {
            ds = createDataSource(outputType);
            outputDataSourceProxy.put(key, ds);
        }
        return (T) ds.createOutputDataSource(config, clazz);
    }

    public abstract <T> T getEnvironment();

    protected abstract DataSource createDataSource(DataSourceType dataSourceType);

    protected abstract Object createInputDataSource(DataSource dataSource, DataSourceConfig config, Class<?> clazz);

    protected abstract Object createOutputDataSource(DataSource dataSource, DataSourceConfig config, Class<?> clazz);
}
