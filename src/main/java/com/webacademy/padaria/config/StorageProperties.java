package com.webacademy.padaria.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String locationPublic;
    private String locationPrivate;

    public String getLocationPublic() {
        return locationPublic;
    }

    public void setLocationPublic(String locationPublic) {
        this.locationPublic = locationPublic;
    }

    public String getLocationPrivate() {
        return locationPrivate;
    }

    public void setLocationPrivate(String locationPrivate) {
        this.locationPrivate = locationPrivate;
    }

}