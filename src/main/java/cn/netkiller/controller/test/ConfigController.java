//package cn.netkiller.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RefreshScope
//@RestController
//public class ConfigController {
//
//	public ConfigController() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@Value("${key}")
//	public String name;
//
//	// @Value("${user.name}")
//	// private String userName;
//
//	@GetMapping("/config")
//	public String config() {
//
//		String name = this.name;
//		return name;
//	}
//}
