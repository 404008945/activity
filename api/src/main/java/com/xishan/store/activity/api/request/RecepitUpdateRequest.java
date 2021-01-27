package com.xishan.store.activity.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RecepitUpdateRequest implements Serializable {

    private Integer id;

    private Integer orderId;

    private Integer activityId;

    private Integer status;

    private Long userId;

    private Date createdAt;

    private String code;
}
