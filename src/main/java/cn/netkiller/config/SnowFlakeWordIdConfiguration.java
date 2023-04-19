package cn.netkiller.config;

import org.springframework.context.annotation.Configuration;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class SnowFlakeWordIdConfiguration {
    static {
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            String addressIp = ip4.getHostAddress();
            System.setProperty("workerId", (Math.abs(addressIp.hashCode()) % 1024) + "");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
