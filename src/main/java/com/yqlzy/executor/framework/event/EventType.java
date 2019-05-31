package com.yqlzy.executor.framework.event;

public class EventType {

    /** 事件类型 */
    private int type;

    public EventType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
