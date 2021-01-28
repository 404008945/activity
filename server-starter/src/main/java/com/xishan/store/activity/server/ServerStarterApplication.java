package com.xishan.store.activity.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDubbo
@EnableTransactionManagement
@EnableScheduling
public class ServerStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerStarterApplication.class, args);
    }

}
