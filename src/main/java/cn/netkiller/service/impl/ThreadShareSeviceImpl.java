package cn.netkiller.service.impl;

import cn.netkiller.service.ThreadSharedSevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ThreadSharedSeviceImpl implements ThreadSharedSevice {
    ThreadLocal<String> threadLocal = new ThreadLocal<String>();

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
