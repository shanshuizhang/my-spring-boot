package com.zss.demo5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class SystemPropertiesCommonLineRunner implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        log.info("命令行runner启动");
        String[] active = context.getEnvironment().getActiveProfiles();
        String[] defalut = context.getEnvironment().getDefaultProfiles();
        String port = context.getEnvironment().getProperty("server.port");
        boolean logger = context.getEnvironment().containsProperty("logger");
        Arrays.stream(active).forEach(a ->{
            log.info("a:{}",a);
        });
        log.info("active内容：{},数组长度：{}",String.join(",",active),active.length);
        log.info("defalut内容：{}",String.join(",",defalut));
        log.info("端口号：{},日志：{}",port,logger);
    }
}
