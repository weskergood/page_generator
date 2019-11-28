package com.foundersc.pg;

import com.alibaba.druid.pool.DruidDataSource;
import com.foundersc.pg.views.ColumnView;
import com.foundersc.pg.views.TableView;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 核心类
 */
@SpringBootApplication
@Data
@Slf4j
public class PageGenerator {

    private static List<Class<?>> data;
    private static String URL;
    private static String DRIVER_CLASS_NAME;
    private static String USER_NAME;
    private static String PASSWORD;

    private static Map<String, List<ColumnView>> parsedTableColumns = new HashMap<>();
    private static Map<String, TableView> parsedTables = new HashMap<>();

    public static void initBaseData(Class<?>... classes) {
        data = Arrays.asList(classes);
    }

    public static List<Class<?>> getData() {
        return data;
    }

    public static Map<String, List<ColumnView>> getParsedTableColumns() {
        return parsedTableColumns;
    }

    public static Map<String, TableView> getParsedTables() {
        return parsedTables;
    }

    public static void initDataSource(String url, String driverClassName, String userName, String password) {
        URL = url;
        DRIVER_CLASS_NAME = driverClassName;
        USER_NAME = userName;
        PASSWORD = password;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setUrl(URL);
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    /**
     * `
     * 运行容器
     */
    public static void run() {
        SpringApplication.run(PageGenerator.class);
    }

}