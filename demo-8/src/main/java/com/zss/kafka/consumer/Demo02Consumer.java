package com.zss.kafka.consumer;

import com.zss.kafka.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class Demo02Consumer {

    @KafkaListener(topics = Demo02Message.TOPIC,groupId = "demo01-consumer-batch-group" + Demo02Message.TOPIC)
    public void onMessage(Demo02Message message){
        log.info("[onMessage][线程编号：{}，线程名：{}，消息内容：{}]",
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                message
        );
    }
}
