package com.ki;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = "com.ki")
@MapperScan("com.ki.mapper")  //告诉框架数据库接口在哪个包
public class MistoreServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MistoreServerApplication.class, args);
    }

}
