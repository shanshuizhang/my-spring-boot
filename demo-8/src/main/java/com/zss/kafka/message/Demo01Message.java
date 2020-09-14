package com.zss.kafka.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Demo01Message {

    public static final String TOPIC = "DEMO_06";

    private Integer id;

}
