package com.tuwindi.erp.erpservice.config;

import org.springframework.context.ConfigurableApplicationContext;

public class Config {
    public static ConfigurableApplicationContext context;
    private static Config instance = null;

    private Config() {
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getValue(String cle) {
        return context.getEnvironment().getProperty(cle);
    }
}
