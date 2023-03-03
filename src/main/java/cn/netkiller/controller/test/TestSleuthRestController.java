package cn.netkiller.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.SpanName;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestSleuthRestController {
    private static final Logger logger = LoggerFactory.getLogger(TestSleuthRestController.class);

    @RequestMapping("/tracing")
    @SpanName("calculateTax")
    public String tracing() throws InterruptedException {
        logger.info("Hello with Sleuth");
        Thread.sleep(100);
        return "tracing";
    }

    @RequestMapping("/a")
    @NewSpan
    public String a() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080" + "/test/tracing", String.class);
        Thread.sleep(200);
        return "open " + response.getBody();
    }

    @RequestMapping("/b")
    @NewSpan
    public String b() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080" + "/test/a", String.class);
        Thread.sleep(200);
        return "open " + response.getBody();
    }
//    @Autowired
//    private Tracer tracer;
//
//    @GetMapping("/traceid")
//    public String getSleuthTraceId() {
//        logger.info("Hello with Sleuth");
////        Span span = tracer.currentSpan();
//        Span span = tracer.currentSpan();
//        if (span != null) {
//            logger.info("Trace ID {}", span.context().traceId());
//            logger.info("Span ID {}", span.context().spanId());
//        }
//        return "Hello from Sleuth";
//    }
}
