package com.alex.eko.paragon.utils.rest.doc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Value("${api.base-path}")
    private String apiBasePath;

    public String getApiBasePath() {
        return apiBasePath;
    }
}
