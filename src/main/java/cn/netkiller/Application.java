package cn.netkiller;

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
public class Application {

	@jakarta.annotation.PostConstruct
	public void init() {
		System.out.println("==================== init ====================");
	}

	@jakarta.annotation.PreDestroy
	public void destroy() {
		System.out.println("==================== destroy ====================");
	}

	public static void main(String[] args) {
//		System.out.println("Netkiller bottleneck tool!");
		SpringApplication.run(Application.class, args);
	}
}
