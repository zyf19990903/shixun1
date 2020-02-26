package com.zhengyuanfang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhengyuanfang.mapper")
public class AdnimistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdnimistrationApplication.class, args);
    }

}
