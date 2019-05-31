package com.yqlzy.executor.framework.event.impl.event;

import com.yqlzy.executor.framework.event.CycleEvent;
import com.yqlzy.executor.framework.event.EventParam;
import com.yqlzy.executor.framework.event.EventType;

import java.io.Serializable;

public class FinishedEvent<ID extends Serializable> extends CycleEvent {

    public FinishedEvent(EventType eventType, ID eventId, EventParam... params) {
        super(eventType, eventId, params);
    }

    @Override
    public void call() {
        super.call();
    }
}
