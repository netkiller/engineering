//package cn.netkiller.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
//import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
//import org.springframework.boot.actuate.endpoint.web.EndpointLinksResolver;
//import org.springframework.boot.actuate.endpoint.web.EndpointMapping;
//import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
//import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
//import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.reactive.config.EnableWebFlux;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Configuration
//@EnableOpenApi
//public class SwaggerConfiguration {
//	// @Value("${swagger.enabled}")
//	// Boolean swaggerEnabled;
//
//	@Bean
//	public Docket petApi() {
//		ApiInfo apiInfo = new ApiInfoBuilder().title("Spring Boot swagger3 整合").contact(new Contact("IT实验室", "https://itlab1024.com", "itlab1024@163.com")).description("Spring Boot 版本2.7、Swagger3 整合").license("Apache 2.0").licenseUrl("https://raw.githubusercontent.com/itlab1024/spring-boot-swagger3-tutorial/main/LICENSE").version("1.0").termsOfServiceUrl("服务条款URL").build();
//		return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo)
//				// .enable(true)
//				.select().apis(RequestHandlerSelectors.any())
//				// .apis(RequestHandlerSelectors.basePackage("cn.netkiller.controller"))
//				.paths(PathSelectors.any()).build().pathMapping("/").directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class).useDefaultResponseMessages(false).enableUrlTemplating(true);
//	}
//}
