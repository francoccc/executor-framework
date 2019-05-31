package com.yqlzy.executor.framework.common;

import com.yqlzy.executor.framework.event.EventType;
import com.yqlzy.executor.framework.event.common.constant.EventTypes;

public class Constants {

    /**
     * 事件类型常量
     */
    public static class EventTypeConstants{
        public static EventType createEventType = new EventType(EventTypes.CREATE.ordinal());
        public static EventType updateEventType = new EventType(EventTypes.UPDATE.ordinal());
        public static EventType finishEventType = new EventType(EventTypes.FINISH.ordinal());
        public static EventType readyCreateEventType = new EventType(EventTypes.READY_CREATE.ordinal());
        public static EventType readyFinishEventType = new EventType(EventTypes.READY_FINISH.ordinal());
        public static EventType finishedEventType = new EventType(EventTypes.FINISHED.ordinal());
    }
}
