package com.cx.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
public class DataSourceConfig {
    @Value("${master.datasource.config.url}")
    private String url;
    @Value("${master.datasource.config.username}")
    private String username;
    @Value("${master.datasource.config.password}")
    private String password;
    @Value("${master.datasource.config.driverClassName}")
    private String driverClassName;
    @Value("${master.datasource.config.initialSize}")
    private int initialSize;
    @Value("${master.datasource.config.minIdle}")
    private int minIdle;
    @Value("${master.datasource.config.maxIdle}")
    private int maxIdle;
    @Value("${master.datasource.config.maxActive}")
    private int maxActive;
    @Value("${master.datasource.config.maxWait}")
    private long maxWait;
    @Value("${master.datasource.config.validationQuery}")
    private String validationQuery;
    @Value("${master.datasource.config.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${master.datasource.config.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${master.datasource.config.testWhileIdle}")
    private Boolean testWhileIdle;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }
}
