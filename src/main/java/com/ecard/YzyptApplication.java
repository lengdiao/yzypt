package com.ecard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.ecard.dao")
@EnableTransactionManagement
@EnableScheduling
public class YzyptApplication {

    public static void main(String[] args) {
        SpringApplication.run(YzyptApplication.class, args);
    }

}
