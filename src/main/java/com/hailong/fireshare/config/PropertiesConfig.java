package com.hailong.fireshare.config;

import com.hailong.fireshare.utils.PropertiesUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class PropertiesConfig {

    @Resource
    private Environment environment;

    @PostConstruct
    public void setProperties() {
        PropertiesUtil.setEnvironment(environment);
    }
}
