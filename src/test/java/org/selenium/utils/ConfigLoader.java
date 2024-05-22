package org.selenium.utils;

import org.selenium.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env)) {
            case STAGE -> properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
            case PRODUCTION -> properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
            default -> throw new IllegalStateException("Invalid env type: " + env);
        }

    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prob = properties.getProperty("baseUrl");
        if (prob != null) return prob;
        else throw new RuntimeException("Property baseUrl is not specified in the stg_config.properties file");
    }

    public String getUsername() {
        String prob = properties.getProperty("username");
        if (prob != null) return prob;
        else throw new RuntimeException("Property baseUrl is not specified in the stg_config.properties file");
    }

    public String getPassword() {
        String prob = properties.getProperty("password");
        if (prob != null) return prob;
        else throw new RuntimeException("Property baseUrl is not specified in the stg_config.properties file");
    }

}
