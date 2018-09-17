package com.doublexman.uum.config;

import com.doublexman.common.auth.AppAuthProvider;
import com.doublexman.common.auth.UserAuthProvider;
import com.doublexman.common.auth.filter.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@Configuration
@Import(SecurityProblemSupport.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MicroserviceSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final SecurityProblemSupport problemSupport;

    private final ApplicationProperties applicationProperties;

    public MicroserviceSecurityConfiguration(SecurityProblemSupport problemSupport, ApplicationProperties applicationProperties) {
        this.problemSupport = problemSupport;
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public CustomSecurityConfigurer customSecurityConfigurer(){
        return new CustomSecurityConfigurer(new JWTFilter(appAuthProvider(), userAuthProvider()));
    }

    @Bean
    public UserAuthProvider userAuthProvider(){
        return new UserAuthProvider(applicationProperties.getJwt().getSecret_key(), applicationProperties.getJwt().getToken_validity_in_seconds());
    }

    @Bean
    public AppAuthProvider appAuthProvider(){
        return new AppAuthProvider(applicationProperties.getJwt().getSecret_key(), applicationProperties.getJwt().getToken_validity_in_seconds());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/bower_components/**")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/swagger-ui/index.html")
                .antMatchers("/test/**")
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**/web-api/**").authenticated()
                .antMatchers("/**/mobile-api/**").authenticated()
                .antMatchers("/**/swagger-ui.html").permitAll()
                .antMatchers("/**/auth/login").permitAll()
                .and()
                .apply(customSecurityConfigurer());
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
