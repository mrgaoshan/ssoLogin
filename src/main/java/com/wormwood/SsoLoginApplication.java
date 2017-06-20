package com.wormwood;

import com.google.common.collect.Lists;
import com.wormwood.config.AddHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SsoLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoLoginApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		AddHeaderFilter addHeaderFilter = new AddHeaderFilter();
		registrationBean.setFilter(addHeaderFilter);
		registrationBean.setUrlPatterns(Lists.newArrayList("/toProject"));
		return registrationBean;
	}
}
