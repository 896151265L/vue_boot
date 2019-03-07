package cn.luoqikun.vue_boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.luoqikun.vue_boot.mapper")//启动的时候扫描mapper
public class APP {

    public static void main(String[] args) {
        SpringApplication.run(APP.class, args);
    }

}

