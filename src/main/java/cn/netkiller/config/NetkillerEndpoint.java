package cn.netkiller.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@Endpoint(id = "netkiller")
public class TestEndpoint {
    @ReadOperation
    public Map<String, Object> threadPoolsMetric() {
        Map<String, Object> metricMap = new HashMap<>();
        List<Map> threadPools = new ArrayList<>();
        Map<String, Object> poolInfo = new HashMap<>();
        poolInfo.put("thread.pool.name", "netkiller");
        poolInfo.put("thread.pool.core.size", 100);
        poolInfo.put("thread.pool.time", new Date());
        threadPools.add(poolInfo);
        metricMap.put("netkiller", threadPools);
        return metricMap;
    }
}
