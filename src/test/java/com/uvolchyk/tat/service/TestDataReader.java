package com.uvolchyk.tat.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private ResourceBundle resourceBundle;

    public TestDataReader() {
        this.resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));
    }

    public Double getDoubleData(String key) {
        return Double.valueOf(getStringData(key));
    }

    public String getStringData(String key){
        return resourceBundle.getString(key);
    }
}
