package cn.netkiller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@PostConstruct
	public void init() {
		System.out.printf("==================== init ====================");
		logger.warn(MarkerFactory.getMarker("finance"), "XXX 系统启动");
	}

	@PreDestroy
	public void destroy() {
		System.out.printf("==================== destroy ====================");
		logger.warn(MarkerFactory.getMarker("finance"), "XXX 系统销毁");
	}

	public static void main(String[] args) {
		System.out.println("Netkiller bottleneck tool!");
		SpringApplication.run(Application.class, args);
	}
}
