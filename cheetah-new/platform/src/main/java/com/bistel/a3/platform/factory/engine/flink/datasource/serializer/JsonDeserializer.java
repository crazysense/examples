package com.bistel.a3.platform.factory.engine.flink.datasource.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by kidki on 2016-08-15.
 */
public class JsonDeserializer<T> implements DeserializationSchema<T> {
    private final Class<T> clazz;

    private static final Logger U = LoggerFactory.getLogger(JsonDeserializer.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public JsonDeserializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(byte[] bytes) throws IOException {
        try {
            return mapper.readValue(bytes, clazz);
        } catch (Exception e) {
            U.error(String.format("Failed to deserialization : type of %s", clazz.getName()));
        }
        return null;
    }

    @Override
    public boolean isEndOfStream(T t) {
        return false;
    }

    @Override
    public TypeInformation<T> getProducedType() {
        return TypeInformation.of(clazz);
    }
}
