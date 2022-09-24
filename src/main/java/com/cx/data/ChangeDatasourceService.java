package com.cx.data;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChangeDatasourceService {

    private Object lock;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Autowired
    private ApplicationContext applicationContext;

    private static ConcurrentHashMap<Object,Object> CACHE_KEY = new ConcurrentHashMap();

    public void changDatasource(String key) throws MysqlException, SQLException {
        if (StringUtils.isEmpty(key)){
            return;
        }
        lock = new Object();
        synchronized (lock){
            if (!CACHE_KEY.containsKey(key)) {
                String newUrl = getNewUrl(dataSourceConfig.getUrl(),key);
                checkUrl(newUrl);
                DataSource dataSource = getNewDataSource(newUrl);
                CACHE_KEY.put(key,dataSource);
                DynamicDatasource dynamicDatasource = (DynamicDatasource)applicationContext.getBean("dynamicDatasource");
                Map<Object, Object> dbs = new HashMap<>();
                for (Map.Entry<Object, Object> entry: CACHE_KEY.entrySet()){
                    dbs.put(entry.getKey(), entry.getValue());
                }
                dynamicDatasource.setTargetDataSources(dbs);
                dynamicDatasource.afterPropertiesSet();
            }
            ThreadLocalUtil.setDB(key);
        }
    }

    private DataSource getNewDataSource(String newUrl) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(newUrl);
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


    private void checkUrl(String newUrl) throws MysqlException, SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(newUrl,dataSourceConfig.getUsername(),dataSourceConfig.getPassword());
        } catch (SQLException e) {
            throw new MysqlException("切换目标数据源异常");
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private String getNewUrl(String url, String key) {
        String var1 = StringUtils.substringAfterLast(url,"/");
        String var2 = StringUtils.substringBeforeLast(var1,"?");
        return StringUtils.replace(url,var2,key);
    }
}
