package cn.netkiller.record;

import java.io.Serializable;

public record RestfulResponse() {
    // boolean status, int code, String reason, Object data
    private static boolean status;
    private static String reason;
    private static int code;
    private static Object data;
}
