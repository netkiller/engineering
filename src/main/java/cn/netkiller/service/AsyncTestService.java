package cn.netkiller.service;

public interface AsyncTestService {
    void asyncDemo1();


    void sleep(Long i);

    void asyncSynchronized(String lockName);

    void asyncMutexDemo(String queue);

    boolean isLock(String lockName);

    void doSomething(String name) throws InterruptedException;
}
