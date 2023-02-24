package cn.netkiller.controller.contract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/contract")
@Tag(name = "合同模块")
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);

    public ContractController() {
        // TODO Auto-generated constructor stub
    }

    @Operation(summary = "普通body请求")
    @GetMapping("/index")
    public String index() {
        return "Hello world!!!\r\n";
    }

    @Operation(summary = "创建合同")
    @Parameters({@Parameter(name = "id", description = "文件id", in = ParameterIn.PATH), @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER), @Parameter(name = "name", description = "文件名称", required = true, in = ParameterIn.QUERY)})
    @PostMapping("/create")
    public String create() {
        return "OK!!!\r\n";
    }

    @Operation(summary = "删除合同")
    @PostMapping("/delete")
    public String delete() {
        return "OK!!!\r\n";
    }

    @Operation(summary = "更新合同")
    @PostMapping("/update")
    public String update() {
        return "OK!!!\r\n";
    }

    @Operation(summary = "读取合同")
    @PostMapping("/read")
    public String read() {
        return "OK!!!\r\n";
    }

    @Operation(summary = "比较合同")
    @PostMapping("/diff")
    public String diff() {
        return "OK!!!\r\n";
    }

    @Operation(summary = "比较合同")
    @PostMapping("/approval")
    public String approval() {
        return "OK!!!\r\n";
    }

    @Operation(summary = "合同审阅")
    @PostMapping("/review")
    public String review() {
        return "OK!!!\r\n";
    }

    @Operation(summary = "合同分享")
    @PostMapping("/share")
    public String share() {
        return "OK!!!\r\n";
    }

    @Operation(summary = "合同打印")
    @PostMapping("/print")
    public String print() {
        return "OK!!!\r\n";
    }
}
