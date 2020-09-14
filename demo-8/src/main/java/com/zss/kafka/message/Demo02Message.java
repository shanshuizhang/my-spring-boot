package com.zss.kafka.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Demo02Message {

    public static final String TOPIC = "DEMO_12";

    private Integer id;

}
