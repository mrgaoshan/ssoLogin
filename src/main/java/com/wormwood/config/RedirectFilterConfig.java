package com.wormwood.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wormwood.service.TokenService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * Created by kasimodo on 2017-07-05.
 */
@Configuration
@EnableConfigurationProperties(RedirectFilterConfig.RedirectProperties.class)
public class RedirectFilterConfig {
    @Bean
    public TokenService tokenService() {
        return new TokenService();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(RedirectProperties redirectProperties) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        Map map = Maps.newHashMap();
        for (RedirectDetailProperties item : redirectProperties.getSystemdetail()) {
            map.put(item.getName(), item.getUrl());
        }
        AddHeaderFilter addHeaderFilter = new AddHeaderFilter(tokenService(), map);
        registrationBean.setFilter(addHeaderFilter);
        registrationBean.setUrlPatterns(Lists.newArrayList("/toProject"));
        return registrationBean;
    }


    @ConfigurationProperties(prefix = "redirect")
    public static class RedirectProperties {
        @NestedConfigurationProperty
        private List<RedirectDetailProperties> systemdetail;


        public List<RedirectDetailProperties> getSystemdetail() {
            return systemdetail;
        }

        public void setSystemdetail(List<RedirectDetailProperties> systemdetail) {
            this.systemdetail = systemdetail;
        }
    }

    @ConfigurationProperties(prefix = "systemdetail")
    public static class RedirectDetailProperties {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
