package com.zss.kafka.producer;

import com.zss.kafka.message.Demo04Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class Demo04Producer {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    public SendResult<Object, Object> syncSend(Integer id) throws ExecutionException, InterruptedException {
        Demo04Message message = new Demo04Message();
        message.setId(id);

        return kafkaTemplate.send(Demo04Message.TOPIC,message).get();
    }
}
