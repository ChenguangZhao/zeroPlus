package com.zpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/19
 */
@SpringBootApplication
@ImportResource(locations = "classpath*:/applicationContext.xml")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
