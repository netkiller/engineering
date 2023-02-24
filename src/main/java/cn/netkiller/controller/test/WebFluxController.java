package cn.netkiller.controller.test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WebFluxController {
	private static final Logger logger = LoggerFactory.getLogger(WebFluxController.class);

	public WebFluxController() {
	}

	// 阻塞5秒钟
	private String job() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
		return "Hellowoard!!!";
	}

	// SpringMVC 方式
	@GetMapping("/SpringMVC")
	private String springmvc() {
		logger.info("start");
		String result = job();
		logger.info("done");
		return result;
	}

	// WebFlux 方式
	@GetMapping("/WebFlux")
	private Mono<String> webflux() {
		logger.info("start");
		Mono<String> result = Mono.fromSupplier(() -> job());
		logger.info("done");
		return result;
	}

	// WebFlux 服务器推送(SSE - >Server Send Event)
	@GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	private Flux<String> flux() {
		Flux<String> result = Flux.fromStream(IntStream.range(1, 10).mapToObj(i -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
			logger.info("sse " + i);
			return "flux data -- " + i;
		}));
		return result;
	}
}
