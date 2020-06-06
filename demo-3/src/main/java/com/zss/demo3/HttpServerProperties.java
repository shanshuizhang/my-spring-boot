package com.zss.demo3;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "http-server")
@Data
public class HttpServerProperties {

    private Integer port;
}
