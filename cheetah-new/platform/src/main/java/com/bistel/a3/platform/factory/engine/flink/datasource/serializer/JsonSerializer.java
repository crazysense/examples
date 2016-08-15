package com.bistel.a3.platform.factory.engine.flink.datasource.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.util.serialization.SerializationSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kidki on 2016-08-15.
 */
public class JsonSerializer<T> implements SerializationSchema<T> {
    private final Class<T> clazz;

    private static final Logger U = LoggerFactory.getLogger(JsonSerializer.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final byte[] emptyBytes = "".getBytes();

    public JsonSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T data) {
        if (mapper.canSerialize(clazz)) {
            try {
                return mapper.writeValueAsBytes(data);
            } catch (JsonProcessingException e) {
                U.error(String.format("Failed to serialization : type of %s", clazz.getName()));
            }
        } else {
            U.error(String.format("Cannot serialization : type of %s class", clazz.getName()));
        }
        return emptyBytes;
    }
}
