package cn.netkiller.controller.test;//package cn.netkiller.controller;

import cn.netkiller.service.AsyncTestService;
import cn.netkiller.service.ThreadShareSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import cn.netkiller.thread.ThreadManager;

//@RefreshScope
@RestController
@Slf4j
@RequestMapping("/thread")
public class TestThreadController {
    @Autowired
    private AsyncTestService asyncTestService;
    @Autowired
    private ThreadManager threadManager;

    public TestThreadController() {

    }

    @GetMapping("/lock/{id}")
    public String lock1(@PathVariable("id") String id) throws InterruptedException {
        synchronized (id.intern()) {
            log.info(Thread.currentThread().getName() + " 上锁");
            Thread.sleep(1000);
            log.info(Thread.currentThread().getName() + " 解锁");
        }
        return Thread.currentThread().getName();
    }

    private final Map<String, Object> share = new ConcurrentHashMap<>();

    @GetMapping("/share/{id}")
    public Map<String, Object> shareTest(@PathVariable("id") String id) throws InterruptedException {

//        share.computeIfAbsent(id, k -> new Object());
        share.computeIfAbsent(id, key -> {
            return new Date();
        });

        synchronized (share) {
            log.info(Thread.currentThread().getName() + " 上锁");
            Thread.sleep(1000);
            log.info(Thread.currentThread().getName() + " 解锁");
        }
        return share;
    }

    @Autowired
    private ThreadShareSevice threadShareSevice;

    @GetMapping("/share/{key}/{value}")
    public Map<String, Object> shareData(@PathVariable("key") String key, @PathVariable("value") String value) throws InterruptedException {
        threadShareSevice.set(key, value);
        return threadShareSevice.get();
    }

    @GetMapping("/async/synchronized")
    public String asyncSynchronized() {
        log.info("接口调用：【开始】");
        try {
            asyncTestService.asyncSynchronized("test");
        } catch (Exception e) {
            return "Exception";
        }
        log.info("接口调用成功：【异步返回】");
        return "Success\n";
    }


    @GetMapping("/async/mutex")
    public String asyncMutex() {
        if (!asyncTestService.isLock("mutex")) {
            asyncTestService.asyncMutexDemo("mutex");
            return ("Success");
        }
        return ("Failure - 执行中，请稍后重试");
    }

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping("/task/{name}")
    public String lockTest(@PathVariable("name") String name) throws InterruptedException {
        asyncTestService.doSomething(name);
        return this.pool();
    }

    @GetMapping("/batch/{name}/{count}")
    public String batch(@PathVariable("name") String name, @PathVariable("count") int count) {
        IntStream.range(0, count).forEach(i -> {
            try {
                asyncTestService.doSomething(name + "-" + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TaskRejectedException e) {
                log.info("ERROR: {}, {}", i, e.getMessage());
                return;
            }
        });

        return this.pool();
    }

    @GetMapping("/pool")
    public String pool() {
        int activeCount = threadPoolTaskExecutor.getActiveCount();
        long completedTaskCount = threadPoolTaskExecutor.getThreadPoolExecutor().getCompletedTaskCount();
        long taskCount = threadPoolTaskExecutor.getThreadPoolExecutor().getTaskCount();
        int queue = threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().size();
        String monitor = String.format("Task: %d, Queue: %d, Active: %d, Completed: %d\n", taskCount, queue, activeCount, completedTaskCount);
        log.info(monitor);
        return monitor;
    }

    @GetMapping("/list")
    public ResponseEntity<String> list() {
        String ps = threadManager.show();
        return ResponseEntity.ok(ps);
    }


    @GetMapping("/stop/{threadId}")
    public ResponseEntity<String> stop(@PathVariable Long threadId) {
        System.out.println(threadId);
        try {
            log.info("Thread id: {}", threadId);
            threadManager.stop(threadId);
            return ResponseEntity.ok("true");
        } catch (Exception e) {
            return ResponseEntity.ok(e.toString());
        }
    }

    @GetMapping("/lists/html")
    public ResponseEntity<String> html() {
        String lists = threadManager.show();
        String html = String.format("<html><head><title>thread list</title></head><body><pre>%s</pre></body></html>", lists);
        return ResponseEntity.ok(html);
    }

    @GetMapping("/async")
    public String async() {
        log.info("接口调用：【开始】 --------------------");
        try {
            // 执行异步任务 - 自定义线程池
            asyncTestService.asyncDemo1();
        } catch (Exception e) {
            return "Exception";
        }
        log.info("接口调用：【结束】 --------------------");
        return "success";
    }

    @GetMapping("/new")
    public String newThread() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    System.out.println("信号中断" + e.toString());
                    e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();
        return "OK";
    }


    @GetMapping("/completableFutureRun")
    public String completableFutureRun() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - CompletableFuture 前置任务");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture thenRun = completableFuture.thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + " - 接着执行第二个 thenRun 任务");
        });
        CompletableFuture thenRunAsync = completableFuture.thenRunAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - 接着执行第二个 thenRunAsync 任务");
        });
        return "Done";
    }

    @GetMapping("/completableFutureAccept")
    public String completableFutureAccept() {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            log.info(Thread.currentThread().getName() + " - CompletableFuture 前置任务");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "前置任务执行完成";
        });
        CompletableFuture<Void> thenAccept = supplyAsync.thenAccept((rev) -> {
            log.info(Thread.currentThread().getName() + " - 接着执行第二个 thenAccept 任务");
            log.info("前置任务返回值：" + rev);
        });
        CompletableFuture<Void> thenAcceptAsync = supplyAsync.thenAcceptAsync((rev) -> {
            log.info(Thread.currentThread().getName() + " - 接着执行第二个 thenAcceptAsync 任务");
            log.info("前置任务返回值：" + rev);
        });
        return "Done";
    }

    @GetMapping("/completableFutureApply")
    public String completableFutureApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            log.info(Thread.currentThread().getName() + " - CompletableFuture 前置任务");
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "第一步";
        });

        CompletableFuture<String> thenApply = supplyAsync.thenApply((rev) -> {
            log.info(Thread.currentThread().getName() + " - 接着执行第二个 thenApply 任务");
            log.info("前置任务返回值：" + rev);
            return "第二步";
        });

        CompletableFuture<String> thenApplyAsync = supplyAsync.thenApplyAsync((rev) -> {
            log.info(Thread.currentThread().getName() + " - 接着执行第二个 thenApplyAsync 任务");
            log.info("前置任务返回值：" + rev);
            return "第二步";
        });
        log.info("supplyAsync：{}", supplyAsync.get());
        log.info("thenApply：{}", thenApply.get());
        log.info("thenApplyAsync：{}", thenApplyAsync.get());
        return "Done";
    }

    @GetMapping("/completableFutureWhenComplete")
    public String completableFutureWhenComplete() throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            return "前置任务完成";
        }).whenComplete((result, throwable) -> {
            System.out.println("前置任务返回值：" + result);
        });
        System.out.println(completableFuture.get());
        return "Done";
    }

    @GetMapping("/completableFutureExceptionally")
    public String completableFutureExceptionally() throws ExecutionException, InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            throw new RuntimeException();
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return "程序出现异常";
        });

//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
////            throw new RuntimeException();
//            return "程序出现异常";
//        }).exceptionally((e) -> {
//            System.out.println("程序出现异常");
//            return "程序出现异常";
//        });
//        System.out.println(completableFuture.get());

        return "Done";
    }

}