package com.mishadehterenok.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

@Configuration
@ComponentScan(basePackages = {"com.mishadehterenok.project.controller", "com.mishadehterenok.project.service"})
@EnableWebMvc
@Import(value = {ThymeleafConfig.class, InternationalizationConfig.class})
public class WebConfig{

    @Bean
    public Function<String, String> currentUrlWithoutParam() {
        return param -> ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam(param).toUriString();
    }
}
