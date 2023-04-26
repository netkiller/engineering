package cn.netkiller.controller.test;//package cn.netkiller.controller;

//import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

import cn.netkiller.domain.project.Project;
import cn.netkiller.service.AsyncService;
import cn.netkiller.service.thread.NetkillerThreadManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@RefreshScope
@RestController
@Slf4j
@RequestMapping("/thread")
public class TestThreadController {

    public TestThreadController() {

    }

    @Value("${spring.shardingsphere.sharding.tables.test.key-generator.props.worker.id}")
    public String name;

    // @Value("${user.name}")
    // private String userName;

    @GetMapping("/snow")
    public String snow() {
//        SnowflakeShardingKeyGenerator snow = new SnowflakeShardingKeyGenerator();
//        AtomicLong atomic = new AtomicLong(0);
//        snow.generateKey()
//        System.out.println(snow.generateKey().toString());
//        System.out.println("atomic.incrementAndGet() = " + atomic.incrementAndGet());
////        Comparable<?> id = snow.generateKey();
////        return atomic.incrementAndGet();
//        return snow.generateKey().toString();
        return this.name;
    }

    @GetMapping("/show")
    public String show() {
        NetkillerThreadManager ntm = new NetkillerThreadManager();
        return ntm.show();
    }

    @Autowired
    private AsyncService asyncService;
    @Autowired
    private NetkillerThreadManager netkillerThreadManager;

    @GetMapping("/async")
    public String async() {
        log.info("接口调用：【开始】 --------------------");
        try {
            // 执行异步任务 - 自定义线程池
            asyncService.asyncDemo1();
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

    @GetMapping("/ps")
    public ResponseEntity<String> ps() {
        String ps = netkillerThreadManager.show();
        return ResponseEntity.ok(ps);
    }

    @GetMapping("/stop/{threadId}")
    public ResponseEntity<String> stop(@PathVariable Long threadId) {
        System.out.println(threadId);
        try {
            log.info("Thread id: {}", threadId);
            netkillerThreadManager.stop(threadId);
            return ResponseEntity.ok("true");
        } catch (Exception e) {
            return ResponseEntity.ok(e.toString());
        }

    }
}
