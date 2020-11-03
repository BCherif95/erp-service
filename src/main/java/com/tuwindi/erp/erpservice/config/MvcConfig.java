package com.tuwindi.erp.erpservice.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    /*@Value("${file.directory}")
    private String fileDirectory;*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations(new File("/Users/HP/Projects/ERP/Files").toURI().toString());
        // .addResourceLocations(new File(fileDirectory).toURI().toString());
    }
}
