package cn.netkiller.controller.expenses;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses/report")
@Tag(name = "费用模块/报表子模块")
public class ExpensesReportController {
    private static final Logger logger = LoggerFactory.getLogger(ExpensesReportController.class);
    public ExpensesReportController() {
        // TODO Auto-generated constructor stub
    }

    @Operation(summary = "普通body请求")
    @GetMapping("/index")
    public String index() {
        return "Hello world!!!\r\n";
    }
}
