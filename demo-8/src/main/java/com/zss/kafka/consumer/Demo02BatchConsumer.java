package com.zss.kafka.consumer;

import com.zss.kafka.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Demo02BatchConsumer {

    @KafkaListener(topics = Demo02Message.TOPIC,groupId = "demo01-consumer-batch-group" + Demo02Message.TOPIC)
    public void onMessage2(List<Demo02Message> messages){
        log.info("[onMessage2][线程编号：{}，线程名：{}，消息数量：{}，消息内容：{}]",
                Thread.currentThread().getId(),
                Thread.currentThread().getName(),
                messages.size(),
                messages
        );
    }
}
