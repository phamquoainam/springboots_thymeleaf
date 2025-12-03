package com.hoainam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String projectDir = System.getProperty("user.dir");
        
        String uploadPath = "file:/" + projectDir + "/src/main/resources/static/uploads/";

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadPath);
                
        System.out.println("DEBUG: Upload Path is: " + uploadPath);
    }
}