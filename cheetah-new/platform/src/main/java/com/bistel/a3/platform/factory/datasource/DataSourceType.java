package com.bistel.a3.platform.factory.datasource;

/**
 * Created by kidki on 2016-08-15.
 */
public enum DataSourceType {
    unknown, kafka, rabbitmq;

    public static DataSourceType fromValue(String value) {
        String dataSourceType = value.trim().toLowerCase();
        for (DataSourceType type : DataSourceType.values()) {
            if (type.name().equals(dataSourceType)) {
                return type;
            }
        }
        return unknown;
    }
}
