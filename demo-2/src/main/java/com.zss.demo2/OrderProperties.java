package com.zss.demo2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "order")
@Data
public class OrderProperties {

    private Integer payTimeoutSeconds;

    private Integer createFrequencySeconds;

    private String desc;
}
