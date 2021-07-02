package com.athub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.athub.mapperdao")
//@EnableEurekaClient
//@EnableFeignClients
public class GzhApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzhApplication.class, args);
    }

}
