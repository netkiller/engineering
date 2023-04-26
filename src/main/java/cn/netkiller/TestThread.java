package cn.netkiller;


import cn.netkiller.service.thread.NetkillerThreadManager;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TestThread {

//    private static final Logger logger = LoggerFactory.getLogger(TestThread.class);

    public TestThread() {

    }

    @SneakyThrows
    public static void main(String[] args) {

        NetkillerThreadManager ntm = new NetkillerThreadManager();
//        System.out.println(Arrays.stream(ntm.listThreads()).toList());


        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    System.out.println("中断Thread#==" + finalI);
//                        e.printStackTrace();
                }

            });
        }
        threadPool.shutdown();

//        System.out.println(ntm.activeCount());

        System.out.println(ntm.show());
        ntm.stop("pool-1-thread-1");
        ntm.stop("pool-1-thread-2");
        ntm.interrupt("pool-1-thread-3");
        //ThreadExplorer.kill("pool-1-thread-*", truer, "");//stop所有线程名符合pool-1-thread-*的线程
//        System.out.println(ntm.fetchThread("pool-1-thread-8").toString());
//        System.out.println(ntm.fetchThread(25).toString());
        ntm.stop(28L);
        ntm.interrupt(30L);
//        ntm.wait(30L);
        Thread.sleep(1000);// 休眠只是为了后面能够打印的时候准确显示

        System.out.println(ntm.show());

        Thread.sleep(30000);// 休眠只是为了后面能够打印的时候准确显示
        System.out.println(ntm.show());

        // final UUID uuid = UUID();
        // static final SecureRandom secureRandom = new SecureRandom();
        // secureRandom.

        // System.out.println(uuid.toString());

//		System.out.println(id);
        // Marker finance = MarkerFactory.getMarker(LogMarker.finance.toString());
        // Marker customer = MarkerFactory.getMarker(LogMarker.customer.toString());
        // Marker market = MarkerFactory.getMarker(LogMarker.market.toString());
        // logger.info("AAAAAAAAA");
        // logger.info(finance, "BBBBBBBBB");
        // logger.error(finance, "This is a serious an error requiring the admin's attention", new Exception("Just testing"));
        // logger.info(finance, "BBBBBBBBB");
        // logger.info(customer, "BBBBBBBBB");
        // logger.info(market, "BBBBBBBBB");

        // MDC.put("userId","0001");
        // logger.info("0001用户");
        // MDC.clear(); 

        // MDC.put("userId","0002");
        // logger.info("0002用户");
        // MDC.clear();

    }

}
