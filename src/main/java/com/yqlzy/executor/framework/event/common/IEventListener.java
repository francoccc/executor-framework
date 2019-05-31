package com.yqlzy.executor.framework.event.common;

import com.yqlzy.executor.framework.event.EventType;

public interface IEventListener {

    void register(EventType eventType);

    boolean containEventType(EventType eventType);

    void fireEvent(IEvent event);
}
