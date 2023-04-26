//package cn.netkiller.controller.test;
//
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@Slf4j
//@RequestMapping("/redisson")
//public class TestRedisController {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//
//    // redisson操作bean
//    @Resource
//    private Redisson redisson;
//
//    @Autowired
//    RedissonClient redissonClient;
//
//    @RequestMapping("/test")
//    public String test() {
//        System.out.println(redissonClient);
//        return "";
//    }
//
//    @RequestMapping("/lock")
//    public String deductTicket() {
//        String lockKey = "ticket";
//
//        RLock lock = redisson.getLock(lockKey + "1");
//
//        try {
//
//            lock.lock();
//
//            int ticketCount = Integer.parseInt(redisTemplate.opsForValue().get(lockKey));
//            if (ticketCount > 0) {
//                int realTicketCount = ticketCount - 1;
//                log.info("扣除成功：剩余票数：" + realTicketCount);
//                redisTemplate.opsForValue().set(lockKey, realTicketCount + "");
//                return realTicketCount + "";
//            } else {
//                log.error("扣除失败");
//                return "error";
//            }
//
//        } finally {
//            lock.unlock();
//        }
////        return lockKey;
//    }
//
//}
