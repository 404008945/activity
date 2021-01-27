package com.xishan.store.activity.api.enums;

public enum RecepitStatusEnum {
    VALID(1),INVALID(0);
    private int value;
    private RecepitStatusEnum(int value){
        this.value= (byte)value;
    }

    public int getValue(){
        return value;
    }
}
