package cn.netkiller.config;

import cn.netkiller.service.ThreadShareSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Endpoint(id = "netkiller")
public class NetkillerEndpoint {
    private final Map<String, Object> share = new ConcurrentHashMap<>();

    @Autowired
    private ThreadShareSevice threadShareSevice;
    @ReadOperation
    public Map<String, Object> threadPoolsMetric() {
        Map<String, Object> metricMap = new HashMap<>();
        List<Map> threadPools = new ArrayList<>();
        Map<String, Object> poolInfo = new HashMap<>();
        poolInfo.put("thread.pool.name", "netkiller");
        poolInfo.put("thread.pool.core.size", 100);
        poolInfo.put("thread.pool.data", threadShareSevice.get().toString());
        poolInfo.put("thread.pool.time", new Date());
        threadPools.add(poolInfo);
        metricMap.put("netkiller", threadPools);
        return metricMap;
    }
}
