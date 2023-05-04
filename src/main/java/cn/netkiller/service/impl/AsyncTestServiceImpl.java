package cn.netkiller.service.impl;

import cn.netkiller.service.AsyncTestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncTestServiceImpl implements AsyncTestService {
    public static Random random = new Random();
    private final InheritableThreadLocal<Boolean> inheritableThreadLocal = new InheritableThreadLocal<Boolean>();
    private final ThreadLocal<Boolean> threadLocal = new ThreadLocal<Boolean>();

    @Async("asyncExecutor")
    public void asyncDemo1() {
        log.info("asyncDemo1：" + Thread.currentThread().getName() + " 正在执行");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncDemo1：" + Thread.currentThread().getName() + " 执行结束");
    }

    @Async("queueThreadPool")
    public void asyncSynchronized(String lockName) {
        log.info(Thread.currentThread().getName() + " 服务执行");
        synchronized (lockName.intern()) {
            log.info(Thread.currentThread().getName() + " 上锁");
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + " 解锁");
        }
        log.info(Thread.currentThread().getName() + " ---------- 服务结束 ----------");
    }

    private final Map<String, Boolean> lock = new ConcurrentHashMap<String, Boolean>();

    @Async("asyncExecutor")
    public void asyncMutexDemo(String lockName) {

        log.info("锁状态 {}", this.lock.toString());

        if (this.lock.isEmpty() || !this.lock.containsKey(lockName) || this.lock.get(lockName) == false) {
            log.info(Thread.currentThread().getName() + " 上锁");
            this.lock.computeIfAbsent(lockName, k -> true);

            try {
                log.info(Thread.currentThread().getName() + " 服务执行");
                Thread.sleep(5 * 1000);
                log.info(Thread.currentThread().getName() + " 执行完成 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.lock.remove(lockName);
                log.info(Thread.currentThread().getName() + " 解锁");
            }

        } else {
            log.info(Thread.currentThread().getName() + " 执行中，请等待");
        }

    }

    public boolean isLock(String lockName) {
        if (this.lock.isEmpty() || !this.lock.containsKey(lockName)) {
            return false;
        }
        return this.lock.get(lockName);
    }

//    @Async("asyncExecutor")
//    public void lockTest() {
//        log.info("lockTest：" + Thread.currentThread().getName() + " 执行");
//        try {
//            ThreadLocal<Boolean> threadLocal = new ThreadLocal<Boolean>();
//            if (threadLocal.get() == null || !threadLocal.get()) {
//                threadLocal.set(true);
//                log.info("lockTest：" + Thread.currentThread().getName() + " 上锁");
//                Thread.sleep(10 * 1000);
//                threadLocal.set(false);
//                log.info("lockTest：" + Thread.currentThread().getName() + " 解锁");
//            } else {
//                log.info("lockTest：" + Thread.currentThread().getName() + " 运行中");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("lockTest：" + Thread.currentThread().getName() + " 结束");
//    }

    @Async("jobExecutor")
    public void doAsyncTask() throws InterruptedException {
        Thread.currentThread().setName("测试线程-" + random.nextInt(1000));
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async("jobExecutor")
    public Future<String> doFutureTask() throws InterruptedException {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<String>("任务一完成");
    }
    private static int count = 0;
    @Async
    public void doSomething(String name) throws InterruptedException {
        synchronized (name.intern()) {
            count++;
            Thread.currentThread().setName(name);
            long start = System.currentTimeMillis();
            Thread.sleep(1000);
            long end = System.currentTimeMillis();
            log.info(Thread.currentThread().getName() + " 耗时：" + (end - start) + "毫秒");
            log.info(Thread.currentThread().getName() + " ---------- 服务结束 ---------- {}",count);
//        Thread.currentThread().interrupt();
        }
    }

    @Async
    public void sleep(Long millis) {
        log.info("sleep：" + Thread.currentThread().getName() + " 正在执行");
        try {
            Thread.sleep(millis * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("sleep：" + Thread.currentThread().getName() + " 执行结束");
    }
}


