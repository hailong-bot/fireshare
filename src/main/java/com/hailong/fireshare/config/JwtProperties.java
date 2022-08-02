package com.hailong.fireshare.config;

import com.hailong.fireshare.entity.JwtHeader;
import com.hailong.fireshare.entity.JwtPayload;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;
    private JwtHeader header;
    private JwtPayload payload;
}
