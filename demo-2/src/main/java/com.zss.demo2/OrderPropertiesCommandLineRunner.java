package com.zss.demo2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderPropertiesCommandLineRunner implements CommandLineRunner {

    @Autowired
    private OrderProperties orderProperties;

    @Override
    public void run(String... args) throws Exception {
        log.info(
                "支付超时时间[{}]，创建频率时间[{}]，描述：[{}]",
                orderProperties.getPayTimeoutSeconds(),
                orderProperties.getCreateFrequencySeconds(),
                orderProperties.getDesc()
        );
    }
}
