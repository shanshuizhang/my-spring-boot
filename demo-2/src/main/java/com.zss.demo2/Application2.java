package com.zss.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;

@SpringBootApplication
public class Application2 {
    public static void main(String[] args) {
        System.setProperty(ConfigFileApplicationListener.CONFIG_NAME_PROPERTY,"application,rpc");
        SpringApplication.run(Application2.class,args);
    }
}
