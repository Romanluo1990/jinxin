/*
 * Jfz.com Inc.
 * Copyright (c) 2012-2017 All Rights Reserved.
 */

package roman.extramoney.jinxin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

/**
 * Created by wayne.fu on 2017/12/22.
 */
@Slf4j
@EnableWebMvc
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    @Override
    protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
        System.setProperty("spring.jndi.ignore", "true");
        return super.createRootApplicationContext(servletContext);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
