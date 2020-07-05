package com.example.term;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.term.mapper")
public class TermApplication {

    public static void main(String[] args) {
        SpringApplication.run(TermApplication.class, args);
    }

}
