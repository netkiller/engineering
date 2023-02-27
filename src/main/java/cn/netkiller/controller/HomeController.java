package cn.netkiller.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.LogMarker;

@RestController
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public HomeController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/")
	public String index() {
		MDC.put("userId", "0001");
		logger.info("0001用户");
		MDC.clear();

		MDC.put("userId", "0002");
		logger.info("0002用户");
		MDC.clear();
		return "Hello world!!!\r\n";
	}

	@GetMapping("/log")
	public String log() {
		Marker finance = MarkerFactory.getMarker(LogMarker.finance.toString());
		Marker customer = MarkerFactory.getMarker(LogMarker.customer.toString());
		Marker market = MarkerFactory.getMarker(LogMarker.market.toString());
		logger.info("常规日志");
		logger.info(finance, "test");
		// logger.error(finance, "This is a serious an error requiring the admin's attention", new Exception("Just testing"));
		logger.info(finance, "finance");
		logger.info(customer, "customer");
		logger.info(market, "market");
		return "OK!!!\r\n";
	}

	@GetMapping("/log/marker")
	public String marker(@RequestParam("marker") String marker, @RequestParam("msg") String msg) {
		logger.info(MarkerFactory.getMarker(marker), msg);
		msg += "\r\n";
		return msg;
	}

	@GetMapping("/info")
	public String info(@RequestParam("marker") String marker, @RequestParam("msg") String msg) {
		msg += "\r\n";
		List<String> list = new ArrayList<String>();
		list.add("finance");
		list.add("customer");
		if (list.contains(marker)) {
			logger.info(MarkerFactory.getMarker(marker), msg);
		} else {
			logger.info(msg);
		}
		return msg;
	}

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	@GetMapping("/jdbc")
//	public String jdbc() {
//		String query = "SELECT properties_value, price_value, reference_name from ejy_goods_price limit 1";
//		return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
//			System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
//			return ("OK");
//		});
//	}
//
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//
//	@GetMapping("/redis")
//	public String redis() {
//		redisTemplate.opsForValue().set("name", "neo", 10, TimeUnit.SECONDS);
//		String name = (String) redisTemplate.opsForValue().get("name");
//		return name;
//	}


}
