package com.xishan.store.activity.api.enums;

public enum ActivityStatusEnum {
    VALID(1),INVALID(0);
    private int value;
    private ActivityStatusEnum(int value){
        this.value= (byte)value;
    }

    public int getValue(){
        return value;
    }
}
