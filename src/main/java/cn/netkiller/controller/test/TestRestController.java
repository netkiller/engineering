package cn.netkiller.controller.test;//package cn.netkiller.controller;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RefreshScope
@RestController
public class TestRestController {

    public TestRestController() {

    }

//	@Value("${key}")
//	public String name;

    // @Value("${user.name}")
    // private String userName;

//    @GetMapping("/snow")
//    public String snow() {
//        SnowflakeShardingKeyGenerator snow = new SnowflakeShardingKeyGenerator();
//        AtomicLong atomic = new AtomicLong(0);
//        snow.generateKey()
//        System.out.println(snow.generateKey().toString());
//        System.out.println("atomic.incrementAndGet() = " + atomic.incrementAndGet());
////        Comparable<?> id = snow.generateKey();
////        return atomic.incrementAndGet();
//        return snow.generateKey().toString();
//    }
}
