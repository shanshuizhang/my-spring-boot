package com.zss.demo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ValueCommandLineRunner implements CommandLineRunner {

    @Value("${order.pay-timeout-seconds}")
    private Integer payTime;

    @Value("${order.create-frequency-seconds}")
    private Integer createTime;

    @Value("${my-uuid}")
    private String myUUID;

    @Value("${my-name}")
    private String myName;

    @Value("${my-port}")
    private Integer myPort;

    @Override
    public void run(String... args) throws Exception {
        log.info("支付超时时间：[{}]，创建时间：[{}]",payTime,createTime);
        log.info("我的名字：[{}]，我的端口：[{}]，我的UUID：[{}]",myName,myPort,myUUID);
    }
}
