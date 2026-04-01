package com.hulahoop.redback;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// RedBack(통합 어드민 대시보드 백엔드) 애플리케이션 시작점
// MapperScan 으로 백엔드 전역의 MyBatis 매퍼를 Bean으로 스캔
@MapperScan("com.hulahoop.redback")
@SpringBootApplication
public class RedBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedBackApplication.class, args);
    }

}
