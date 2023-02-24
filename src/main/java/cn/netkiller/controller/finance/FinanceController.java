package cn.netkiller.controller.finance;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
@Tag(name = "财务模块")
public class FinanceController {
    private static final Logger logger = LoggerFactory.getLogger(FinanceController.class);
    public FinanceController() {
        // TODO Auto-generated constructor stub
    }

    @Operation(summary = "普通body请求")
    @GetMapping("/index")
    public String index() {
        return "Hello world!!!\r\n";
    }
}
