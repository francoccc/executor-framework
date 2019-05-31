package com.yqlzy.executor.framework.event;

import com.yqlzy.executor.framework.event.common.IEvent;

import java.io.Serializable;

public abstract class AbstractEvent<ID extends Serializable> implements IEvent {

    private EventType eventType;
    private EventParam[] eventParams;
    private ID id;

    @Override
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public EventType getEventType() {
        return this.eventType;
    }

    @Override
    public EventParam[] getParams() {
        return this.eventParams;
    }

    @Override
    public void setParams(EventParam... params) {
        eventParams = params;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
