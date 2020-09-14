package com.zss.kafka;

import com.zss.kafka.producer.Demo01Producer;
import com.zss.kafka.producer.Demo04Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class Demo01ProducerTest {

    @Autowired
    private Demo01Producer demo01Producer;

    @Autowired
    private Demo04Producer demo04Producer;

    @Test
    public void testSyncSend2() throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        int id = (int)(System.currentTimeMillis() / 1000);
        SendResult result = demo01Producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        countDownLatch.await();
    }

    @Test
    public void testASyncSend() throws InterruptedException {
        int id = (int)(System.currentTimeMillis() / 1000);

        demo01Producer.asyncSend(id)
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
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }

    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = demo04Producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
