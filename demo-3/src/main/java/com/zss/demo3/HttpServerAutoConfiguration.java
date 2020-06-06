package com.zss.demo3;

import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
@EnableConfigurationProperties(HttpServerProperties.class)
@Slf4j
public class HttpServerAutoConfiguration {

    @Bean
    @ConditionalOnClass(HttpServer.class)
    public HttpServer httpServer(HttpServerProperties httpServerProperties) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(httpServerProperties.getPort()),0);
        httpServer.start();
        log.info("[httpServer][启动服务成功]：端口为 ：[{}]",httpServerProperties.getPort());
        return httpServer;
    }
}
