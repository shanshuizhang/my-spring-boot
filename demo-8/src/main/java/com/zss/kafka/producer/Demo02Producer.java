package com.zss.kafka.producer;

import com.zss.kafka.message.Demo02Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Demo02Producer {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public ListenableFuture<SendResult<Object,Object>> asyncSend(Integer id){
        Demo02Message message = new Demo02Message();
        message.setId(id);
        //发送异步消息
        return kafkaTemplate.send(Demo02Message.TOPIC,message);
    }
}
