package cn.netkiller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Value("${spring.application.name}")
	public String name;

	@PostConstruct
	public void init() {
		System.out.printf("==================== init ====================");
		logger.error(MarkerFactory.getMarker("finance"), String.format("%s 系统启动", name));
	}

	@PreDestroy
	public void destroy() {
		System.out.printf("==================== destroy ====================");
		logger.error(MarkerFactory.getMarker("finance"), String.format("%s 系统销毁", name));
	}

	public static void main(String[] args) {
		System.out.println("Netkiller bottleneck tool!");
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
		return args -> {
			// log.info("our database URL connection will be " +
			// environment.getProperty("spring.datasource.url"));
			System.out.println(environment.getProperty("spring.application.name"));
		};
	}
}
