package cn.netkiller.service.impl;

import cn.netkiller.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {
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

    public static Random random = new Random();

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

    @Async
    public void test() throws InterruptedException {
        Thread.currentThread().setName("测试线程");
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
    }
}


