package com.webacademy.padaria.config;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final StorageProperties storageProperties;

    public MvcConfig(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String publicPath = Paths.get(storageProperties.getLocationPublic())
                    .toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/imagens-publicas/**")
                .addResourceLocations(publicPath);
    }
}
