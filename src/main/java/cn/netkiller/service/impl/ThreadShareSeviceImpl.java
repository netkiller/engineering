package cn.netkiller.service.impl;

import cn.netkiller.service.ThreadShareSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ThreadShareSeviceImpl implements ThreadShareSevice {
    ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    private final Map<String, Object> share = new ConcurrentHashMap<String, Object>();

    public void set(String key, String value) {
        this.share.computeIfAbsent(key, k -> value);
        synchronized (this.share) {
            log.info(Thread.currentThread().getName() + " 上锁");
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + " 解锁");
        }
    }

    public Map<String, Object> get() {
        return this.share;
    }

    public void setThreadLocal() {
        this.threadLocal.set("OK");

//        IntStream.range(0, 10).forEach(i -> new Thread(() -> {
//            local.set(Thread.currentThread().getName() + ":" + i);
//            System.out.println("线程：" + Thread.currentThread().getName() + ",local:" + local.get());
//        }).start());
    }

    public String getThreadLocal() {
        return this.threadLocal.get();
    }
}
