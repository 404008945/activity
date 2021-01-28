package com.xishan.store.activity.api.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActivityUpdateRequest implements Serializable {
    private Integer id;

    private String name;

    private Integer status;

    private Integer skuId;

    private String description;

    private Integer type;

    private Date startTime;

    private Date endTime;

    private Date createdAt;
}
