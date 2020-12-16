package com.uvolchyk.tat.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static Integer getIntegerData(String key) {
        return Integer.valueOf(TestDataReader.getStringData(key));
    }

    public static Double getDoubleData(String key) {
        return Double.valueOf(TestDataReader.getStringData(key));
    }

    public static String getStringData(String key) {
        return resourceBundle.getString(key);
    }
}
