package com.yqlzy.executor.framework.event.impl.event;

import com.yqlzy.executor.framework.event.CycleEvent;
import com.yqlzy.executor.framework.event.EventParam;
import com.yqlzy.executor.framework.event.EventType;

import java.io.Serializable;

public class UpdateEvent<ID extends Serializable> extends CycleEvent {

    public UpdateEvent(EventType eventType, ID eventId, EventParam... params) {
        super(eventType, eventId, params);
        setUpdateAlive(true);
    }

    @Override
    public void call() {
        super.call();
    }
}
