package com.xishan.store.activity.server.mq.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateOrderMessage implements Serializable {
    private Integer goodsId;
    private Integer skuId;
    private Integer num;
    private Byte payType;
    private Byte type;
    private String code;
    private Integer recepitId;
}
