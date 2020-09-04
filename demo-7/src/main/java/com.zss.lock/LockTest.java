package com.zss.lock;

public class LockTest {

    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        String lock_key = "key1";
        for (int i = 0; i < 10; i++) {
            DistributedReentrantLock.lock(lock_key, 10000L, 1000);
        }
        for (int i = 0; i < 9; i++) {
            DistributedReentrantLock.unlock(lock_key);
        }
    }

    public static void test2() throws Exception {
        String lock_key = "key2";
        DistributedReentrantLock.lock(lock_key, 5000L, 1000);
        Thread thread1 = new Thread(() -> {
            try {
                try {
                    DistributedReentrantLock.lock(lock_key, 5000L, 7000);
                } finally {
                    DistributedReentrantLock.unlock(lock_key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.setName("thread1");
        thread1.start();
        thread1.join();
    }

}
