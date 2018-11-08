package com.example.springfilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class WebConfig {
	//Register ABCFilter
	@Bean
	public FilterRegistrationBean<ABCFilter> abcFilter() {
		FilterRegistrationBean<ABCFilter> filterRegBean = new FilterRegistrationBean<>();
		filterRegBean.setFilter(new ABCFilter());
		filterRegBean.addUrlPatterns("/app/*");
		filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE -1);
		return filterRegBean;
	}

}
