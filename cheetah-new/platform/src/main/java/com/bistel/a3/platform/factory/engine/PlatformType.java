package com.bistel.a3.platform.factory.engine;

/**
 * Created by kidki on 2016-08-15.
 */
public enum PlatformType {
    unknown, flink;

    public static PlatformType fromValue(String value) {
        String platformEngineType = value.trim().toLowerCase();
        for (PlatformType type : PlatformType.values()) {
            if (type.name().equals(platformEngineType)) {
                return type;
            }
        }
        return unknown;
    }
}
