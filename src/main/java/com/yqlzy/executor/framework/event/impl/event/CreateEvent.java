package com.yqlzy.executor.framework.event.impl.event;

import com.yqlzy.executor.framework.event.CycleEvent;
import com.yqlzy.executor.framework.event.EventParam;
import com.yqlzy.executor.framework.event.EventType;

import java.io.Serializable;

/**
 * 创建事件
 * @param <ID>
 */
public class CreateEvent<ID extends Serializable> extends CycleEvent {

    public CreateEvent(EventType eventType, ID eventId, EventParam... params) {
        super(eventType, eventId, params);
    }

    @Override
    public void call() {
        super.call();
    }
}
