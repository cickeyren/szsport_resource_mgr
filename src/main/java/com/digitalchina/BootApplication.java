package com.digitalchina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class BootApplication extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(BootApplication.class);

    /* 这个方法在打包在war包运行时是必需的 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootApplication.class);
    }

    public static void main(String[] args) {
        log.debug("============ Application Starting ===========");
//        TomcatEmbeddedServletContainerFactory factory =
//                new TomcatEmbeddedServletContainerFactory();
        SpringApplication.run(BootApplication.class, args);
    }
}
