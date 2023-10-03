package com.muqi.backendsl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.muqi.backendsl.mapper")
public class BackendSlApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendSlApplication.class, args);
    }

}
