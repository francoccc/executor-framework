package com.yqlzy.executor.framework.event.common;

import com.yqlzy.executor.framework.event.EventParam;
import com.yqlzy.executor.framework.event.EventType;

/**
 *
 */
public interface IEvent {

    /**
     *
     * @param eventType
     */
    void setEventType(EventType eventType);

    /**
     *
     * @return
     */
    EventType getEventType();

    /**
     *
     * @return EventParam
     */
    EventParam[] getParams();

    /**
     *
     * @param params
     */
    void setParams(EventParam... params);

    /**
     *
     */
    void call();
}
