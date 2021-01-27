package com.xishan.store.activity.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RecepitRequest implements Serializable {
    private Integer id;

    private Integer orderId;

    private Integer activityId;

    private Integer status;

    private Long userId;

    private Date createdAt;

    private String code;

    private Integer skuId;

    private Integer num;

    private Integer goodId;

    private byte type;

    private byte payType;


}
