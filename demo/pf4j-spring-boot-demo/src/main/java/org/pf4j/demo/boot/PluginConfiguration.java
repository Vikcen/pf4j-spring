package org.pf4j.demo.boot;

import org.pf4j.demo.boot.util.BeanCheck;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class PluginConfiguration {
    @Bean
    public SpringPluginManager pluginManager() {
        return new SpringPluginManager();
    }
    
    @Bean
    public BeanCheck beanCheck() {
        return new BeanCheck();
    }
    
    @Bean
    @DependsOn("pluginManager")
    public Greetings greetings() {
        return new Greetings();
    }
    
}
