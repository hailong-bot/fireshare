package com.hailong.fireshare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.hailong.fireshare.mapper"})
public class FireshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireshareApplication.class, args);
    }

}
