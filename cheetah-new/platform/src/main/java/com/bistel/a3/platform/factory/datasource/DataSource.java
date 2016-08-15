package com.bistel.a3.platform.factory.datasource;

import java.io.Serializable;

/**
 * Created by kidki on 2016-08-15.
 */
public interface DataSource extends Serializable {
    Object createInputDataSource(DataSourceConfig config, Class<?> clazz);
    Object createOutputDataSource(DataSourceConfig config, Class<?> clazz);
}
