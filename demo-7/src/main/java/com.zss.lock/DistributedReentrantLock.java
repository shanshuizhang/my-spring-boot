package com.zss.lock;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基于MySQL数据库实现 分布式锁
 * 功能：
 *      1。锁具有重入功能，一个使用者可以多次获取同一把锁
 *      2。获取锁有超时的功能，在超时时间内未获取锁则返回false
 *      3。自动容错，持有锁的时候加个超时时间，超时后即使还未释放锁，其他机器也可以获得锁
 * 技术点：
 *     1.数据库乐观锁
 *     2.ThreadLocal
 *
 */
public class DistributedReentrantLock {

    private static final ThreadLocal<String> REQUEST_ID_TL = new ThreadLocal<>();

    /**
     * 获取请求id
     * @return
     */
    private static String getRequestId(){
        String requestId = REQUEST_ID_TL.get();
        if(requestId == null || "".equals(requestId)){
            requestId = UUID.randomUUID().toString();
            REQUEST_ID_TL.set(requestId);
            System.out.println(String.format("请求id：%s",requestId));
        }
        return requestId;
    }

    /**
     * 获取锁
     * @param lockKey 锁唯一标志
     * @param holdTime 毫秒，持有锁的时间，防止死锁
     * @param timeout 毫秒，获取锁等待超时时间，获取不到将重试
     * @return
     */
    public static boolean lock(String lockKey,long holdTime,long timeout) throws Exception {
        System.out.println("开始");
        boolean lockResult = false;
        String requestId = getRequestId();
        long startTime = System.currentTimeMillis();
        while (true){
            LockModel lockModel = DBUtils.get(lockKey);
            if(Objects.isNull(lockModel)){
                //初始化锁
                DBUtils.insert(LockModel.builder().lockKey(lockKey).requestId("").lockCount(0).holdTime(0L).version(0).build());
            }else {
                String reqId = lockModel.getRequestId();
                if("".equals(reqId)){//获取锁
                    lockModel.setRequestId(requestId);
                    lockModel.setLockCount(1);
                    lockModel.setHoldTime(System.currentTimeMillis() + holdTime);
                    if(DBUtils.update(lockModel) == 1){
                        lockResult = true;
                        break;
                    }
                }else if(requestId.equals(reqId)){//重入锁
                    lockModel.setLockCount(lockModel.getLockCount() + 1);
                    lockModel.setHoldTime(System.currentTimeMillis() + holdTime);
                    if(DBUtils.update(lockModel) == 1){
                        lockResult = true;
                        break;
                    }
                }else {
                    if(lockModel.getHoldTime() < System.currentTimeMillis()){//重置其他线程超时的锁
                        resetLock(lockModel);
                    }else {
                        if(startTime + timeout > System.currentTimeMillis()){//获取锁超时时间内每隔100ms重试
                            TimeUnit.MILLISECONDS.sleep(100);
                        }else {//获取锁超时，终止获取
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("结束");
        return lockResult;
    }

    /**
     * 释放锁
     * @param lockKey 锁唯一标志
     */
    public static void unlock(String lockKey) throws Exception {
        String requestId = getRequestId();
        LockModel lockModel = DBUtils.get(lockKey);
        if(Objects.nonNull(lockModel) && requestId.equals(lockModel.getRequestId()) && lockModel.getLockCount() > 0){
            if(lockModel.getLockCount() == 1){
                resetLock(lockModel);
            }else {
                lockModel.setLockCount(lockModel.getLockCount() - 1);
                DBUtils.update(lockModel);
            }
        }
    }

    /**
     * 重置锁
     * @param lockModel
     * @return
     * @throws Exception
     */
    public static int resetLock(LockModel lockModel) throws Exception {
        lockModel.setRequestId("");
        lockModel.setLockCount(0);
        lockModel.setHoldTime(0L);
        return DBUtils.update(lockModel);
    }

}
