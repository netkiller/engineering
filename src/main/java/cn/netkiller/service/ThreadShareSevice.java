package cn.netkiller.service;

import java.util.Map;

public interface ThreadShareSevice {
    Map<String, Object> get();

    void set(String key, String value);
}
