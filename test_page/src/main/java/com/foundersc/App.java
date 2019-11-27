package com.foundersc;

import com.foundersc.pg.PageGenerator;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // 初始化数据源
        PageGenerator.initDataSource("jdbc:mysql://localhost:3306/test?useSSL=false", "com.mysql.jdbc.Driver", "root", "123456");
        // 初始化基本信息
        PageGenerator.initBaseData(User.class, Dept.class);
        // 运行容器
        PageGenerator.run();
    }
}