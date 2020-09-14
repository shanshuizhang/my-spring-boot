package com.zss.kafka;

import com.zss.kafka.producer.Demo02Producer;
import com.zss.kafka.producer.Demo04Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//@ActiveProfiles("batch")
@ActiveProfiles("consumerbatch")
@Slf4j
public class Demo02ProducerTest {

    @Autowired
    private Demo02Producer producer;

    @Test
    public void asyncSendTest() throws InterruptedException {
        log.info("开始执行");
        for(int i = 0;i < 3;i++){
            int id = (int)(System.currentTimeMillis());
            producer.asyncSend(id)
                    .addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
                        @Override
                        public void onFailure(Throwable ex) {
                            log.error("[testASyncSend][发送编号：[{}] 发送异常[{}]]", id, ex);
                        }

                        @Override
                        public void onSuccess(SendResult<Object, Object> result) {
                            log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
                        }
                    });
            Thread.sleep(10 * 1000L);
        }

        new CountDownLatch(1).await();
    }

}
