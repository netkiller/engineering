package cn.netkiller.service.impl;

import cn.netkiller.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

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

}
