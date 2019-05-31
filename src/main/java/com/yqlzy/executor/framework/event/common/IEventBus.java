package com.yqlzy.executor.framework.event.common;

import com.yqlzy.executor.framework.event.AbstractEventListener;

public interface IEventBus {

    void addEventListener(AbstractEventListener listener);

    void removeEvenetListener(AbstractEventListener listener);

    void clearEventListener();

    void addEvent(IEvent event);

    void handleEvent();

    /**
     * 处理事件重要逻辑
     * @param event
     * @throws Exception
     */
    void handleSingleEvent(IEvent event) throws Exception;

    void clearEvent();

    void clear();
}
