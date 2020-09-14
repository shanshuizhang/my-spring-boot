package com.zss.kafka.producer;

import com.zss.kafka.message.Demo01Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
@Component
public class Demo01Producer {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        Demo01Message demo01Message = new Demo01Message();
        demo01Message.setId(1);
        //同步发送消息
        return kafkaTemplate.send(Demo01Message.TOPIC,demo01Message).get();
    }

    public ListenableFuture<SendResult<Object,Object>> asyncSend(Integer id){
        Demo01Message demo01Message = new Demo01Message();
        demo01Message.setId(1);
        //异步发送消息
        return kafkaTemplate.send(Demo01Message.TOPIC,demo01Message);
    }
}
