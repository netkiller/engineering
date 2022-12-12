package cn.netkiller;

//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {

	// @PostConstruct
	// public void init() {
	// System.out.printf("==================== init ====================");
	// }
	//
	// @PreDestroy
	// public void destroy() {
	// System.out.printf("==================== destroy ====================");
	// }

	public static void main(String[] args) {
		System.out.println("Netkiller bottleneck tool!");
		SpringApplication.run(Application.class, args);
	}
}
