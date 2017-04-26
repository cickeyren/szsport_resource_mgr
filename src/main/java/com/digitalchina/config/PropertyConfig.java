package com.digitalchina.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(locations = "classpath:application.properties",prefix="application")
public class PropertyConfig {
    /**
     * #体育资源访问接口地址
     */
    @Value("${sportresource.url}")
    public  String SPORT_RESOURCE_URL;
    @Value("${pageSize}")
    public  String pageSize;

    /**
     * 窗口系统UPMS用户权限接口地址
     */
    @Value("${window.upms.url}")
    public String WINDOW_UPMS_URL;
    @Value("${window.api.key}")
    public String WINDOW_API_KEY;
    @Value("${window.ext1}")
    public String WINDOW_EXT1;
}
