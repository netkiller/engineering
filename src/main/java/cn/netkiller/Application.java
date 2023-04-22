package cn.netkiller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan()
@EnableJpaRepositories()
//@EnableCaching
//@EnableScheduling
@Slf4j
public class Application {

    @Value("${spring.application.name:netkiller}")
    private String name;

    @jakarta.annotation.PostConstruct
    public void init() {
        System.out.println("==================== init {} ====================");
        log.info("==================== start {} ====================", name);
    }

    @jakarta.annotation.PreDestroy
    public void destroy() {
        System.out.println("==================== destroy {} ====================");
        log.info("==================== destroy {} ====================", name);
    }

    public static void main(String[] args) {
//		System.out.println("Netkiller bottleneck tool!");
        SpringApplication.run(Application.class, args);
    }
}
