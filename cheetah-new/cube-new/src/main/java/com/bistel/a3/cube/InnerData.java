package com.bistel.a3.cube;

/**
 * Created by kidki on 2016-08-15.
 */
public class InnerData {
    private String key;
    private String value;

    public InnerData(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "InnerData{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
