package com.wormwood;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wormwood.config.AddHeaderFilter;
import com.wormwood.service.TokenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SsoLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoLoginApplication.class, args);
    }

}
