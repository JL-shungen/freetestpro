package com.itheima;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.100.101:3306/rw?characterEncoding=utf-8", "root", "root");
        System.out.println(connection);
        SpringApplication.run(Application.class,args);
        log.info("项目启动成功...");
    }
}