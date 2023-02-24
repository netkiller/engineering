package cn.netkiller.controller.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/swagger")
@Tag(name = "body参数")
public class SwaggerController {
	private static final Logger logger = LoggerFactory.getLogger(SwaggerController.class);

	public SwaggerController() {
		// TODO Auto-generated constructor stub
	}

	// @ApiOperation(value = "通过ID获取")
	@Operation(summary = "普通body请求")
	@GetMapping("/index")
	public String index() {
		return "Hello world!!!\r\n";
	}

	@Operation(summary = "普通body请求+Param+Header+Path")
	@Parameters({ @Parameter(name = "id", description = "文件id", in = ParameterIn.PATH), @Parameter(name = "token", description = "请求token", required = true, in = ParameterIn.HEADER), @Parameter(name = "name", description = "文件名称", required = true, in = ParameterIn.QUERY) })
	@GetMapping("/log")
	public String log() {
		return "OK!!!\r\n";
	}
}
