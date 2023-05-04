package cn.netkiller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ExecutorConfiguration {
    @Bean("asyncExecutor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置线程名
        executor.setThreadNamePrefix("async-method-execute-");
        //设置核心线程数
        executor.setCorePoolSize(10);
        //设置最大线程数
        executor.setMaxPoolSize(50);
        //线程池所使用的缓冲队列
        executor.setQueueCapacity(100);
        //设置多余线程等待的时间，单位：秒
        executor.setKeepAliveSeconds(10);
        // 初始化线程
        executor.initialize();
        return executor;
    }
}
