package cn.netkiller.thread;

public class ThreadLocaTest {

    private static ThreadLocal<String> local = new ThreadLocal<String>();
    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：" + local.get());
                ThreadLocaTest.local.set("thread_A");
                System.out.println(Thread.currentThread().getName() + "：" + local.get());
            }
        }, "A").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "：" + local.get());
                ThreadLocaTest.local.set("thread_B");
                System.out.println(Thread.currentThread().getName() + "：" + local.get());
                local.remove();
                System.out.println("remove: " + local.get());
            }
        }, "B").start();
    }
}