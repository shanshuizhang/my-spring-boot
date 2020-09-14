package com.zss.kafka.consumer;

import com.zss.kafka.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo01AConsumer {

    @KafkaListener(topics = Demo01Message.TOPIC,groupId = "demo01-A-consumer-group-" + Demo01Message.TOPIC)
    public void onMessage(ConsumerRecord<Integer, String> record){
        log.info("[onMessage][线程编号：{}，线程名称：{}，消息内容{}]",Thread.currentThread().getId(),
                Thread.currentThread().getName(),record);
    }
}
