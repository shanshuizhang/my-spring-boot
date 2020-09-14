package com.zss.kafka.consumer;

import com.zss.kafka.message.Demo04Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo04Consumer {

    @KafkaListener(topics = Demo04Message.TOPIC,groupId = "retry-consumer-group" + Demo04Message.TOPIC)
    public void onMessage(Demo04Message message){
        log.info("[onMessage][线程编号：{}，消息内容{}]",Thread.currentThread().getId(),message);
        throw new RuntimeException("onMessage消费异常");
    }
}
