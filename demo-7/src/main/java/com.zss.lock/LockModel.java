package com.zss.lock;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LockModel {

    private String requestId;

    private String lockKey;

    private Integer version;

    private Integer lockCount;

    private Long holdTime;

}
