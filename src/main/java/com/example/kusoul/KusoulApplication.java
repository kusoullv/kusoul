package com.example.kusoul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.maqh.demo.dao")
public class KusoulApplication {

    public static void main(String[] args) {
        SpringApplication.run(KusoulApplication.class, args);
    }

}
