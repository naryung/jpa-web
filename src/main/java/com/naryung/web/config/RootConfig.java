package com.naryung.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.naryung.web", excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {

}
