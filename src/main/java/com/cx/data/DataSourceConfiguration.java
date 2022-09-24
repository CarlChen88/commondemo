package com.cx.data;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AutoConfigureAfter(DataSourceConfig.class)
@MapperScan(basePackages = {"com.cx.dao"},sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceConfiguration {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Bean("masterDataSource")
    @Primary
    public DataSource setDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dataSourceConfig.getUrl());
        druidDataSource.setUsername(dataSourceConfig.getUsername());
        druidDataSource.setPassword(dataSourceConfig.getPassword());
        druidDataSource.setInitialSize(dataSourceConfig.getInitialSize());
        druidDataSource.setMinIdle(dataSourceConfig.getMinIdle());
        druidDataSource.setMaxActive(dataSourceConfig.getMaxActive());
        druidDataSource.setMaxWait(dataSourceConfig.getMaxWait());
        druidDataSource.setValidationQuery(dataSourceConfig.getValidationQuery());
        druidDataSource.setTestOnBorrow(dataSourceConfig.getTestOnBorrow());
        druidDataSource.setTestOnReturn(dataSourceConfig.getTestOnReturn());
        druidDataSource.setTestWhileIdle(dataSourceConfig.getTestWhileIdle());
        return druidDataSource;
    }

    @Bean("dynamicDatasource")
    @Primary
    public DynamicDatasource setDynamicDatasource(@Qualifier("masterDataSource") DataSource dataSource) {
        DynamicDatasource dynamicDatasource = new DynamicDatasource();
        Map<Object, Object> dbs = new HashMap<>();
        dbs.put("master_db",dataSource);
        dynamicDatasource.setTargetDataSources(dbs);
        dynamicDatasource.setDefaultTargetDataSource(dataSource);
        dynamicDatasource.afterPropertiesSet();
        return dynamicDatasource;
    }

    @Bean
    @Primary
    public PlatformTransactionManager setTransactionManager(@Qualifier("dynamicDatasource") DynamicDatasource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("sqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactoryBean(@Qualifier ("dynamicDatasource") DynamicDatasource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        /*ClassPathResource classPathResource = new ClassPathResource("mybatis-config.xml");
        if (classPathResource.exists()) {
            sqlSessionFactoryBean.setConfigLocation(classPathResource);
        }*/
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
