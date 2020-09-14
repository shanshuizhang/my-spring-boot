package com.zss.kafka.consumer;

import com.zss.kafka.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo01Consumer {

    @KafkaListener(topics = Demo01Message.TOPIC,groupId = "demo01-consumer-group-" + Demo01Message.TOPIC)
    public void onMessage(Demo01Message demo01Message){
        log.info("[onMessage][线程编号：{}，线程名称：{}，消息内容{}]",Thread.currentThread().getId()
                ,Thread.currentThread().getName(),demo01Message);
    }
}
