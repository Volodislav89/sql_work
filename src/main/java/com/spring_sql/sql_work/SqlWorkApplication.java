package com.spring_sql.sql_work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SqlWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlWorkApplication.class, args);
    }

}
