package com.digitalchina.config;

import java.util.UUID;

/**
 * Created by 胡本强 on 2016-6-21.
 */
public class UUIDUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
