package com.dubbo.api.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-12:10:59
 * Modify date: 2019-07-12:10:59
 */
@Slf4j
@SpringBootConfiguration
public class DataSourceConfiguration {
    @Value("${server.datasource.driverClassName}")
    private String driver;
    @Value("${server.datasource.url}")
    private String url;
    @Value("${server.datasource.username}")
    private String username;
    @Value("${server.datasource.password}")
    private String password;
    @Bean
    public DataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setAutoCommitOnClose(false);
        dataSource.setInitialPoolSize(10);
        dataSource.setMinPoolSize(10);
        dataSource.setMaxPoolSize(100);
        return dataSource;
    }
}
