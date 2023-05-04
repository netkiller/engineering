package cn.netkiller.controller.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigRestController {

    public ConfigRestController() {
        // TODO Auto-generated constructor stub
    }

    @Value("${spring.shardingsphere.sharding.tables.test.key-generator.props.worker.id}")
    public String workerId;

    @GetMapping("/workerId")
    public String snow() {
        return this.workerId;
    }
//    @Value("${key}")
//    public String name;

    // @Value("${user.name}")
    // private String userName;

//    @GetMapping("/config")
//    public String config() {
//        String name = this.name;
//        return name;
//    }
}
