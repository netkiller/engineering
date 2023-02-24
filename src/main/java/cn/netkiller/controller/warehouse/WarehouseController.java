package cn.netkiller.controller.warehouse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse")
@Tag(name = "仓储模块")
public class WarehouseController {
    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);
    public WarehouseController() {
        // TODO Auto-generated constructor stub
    }

    @Operation(summary = "")
    @GetMapping("/index")
    public String index() {
        return "Hello world!!!\r\n";
    }
}
