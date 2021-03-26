package com.example.signuponline;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * ...
 *
 * @author yudh
 * @date 2021-03-01 21:20:27
 */
@EnableCaching
@SpringBootApplication
@MapperScan("com.example.signuponline.mapper")
public class SignuponlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignuponlineApplication.class, args);
    }

}
