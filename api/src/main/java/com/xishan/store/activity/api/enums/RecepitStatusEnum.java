package com.xishan.store.activity.api.enums;

public enum RecepitStatusEnum {
    VALID(1),WAIT(0),INVALID(2);
    private int value;
    private RecepitStatusEnum(int value){
        this.value= (byte)value;
    }

    public int getValue(){
        return value;
    }
}
