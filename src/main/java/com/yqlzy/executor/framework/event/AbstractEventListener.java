package com.yqlzy.executor.framework.event;

import com.yqlzy.executor.framework.event.common.IEvent;
import com.yqlzy.executor.framework.event.common.IEventListener;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class AbstractEventListener implements IEventListener {

    private Set<EventType> set;

    public AbstractEventListener() {
        this.set = new CopyOnWriteArraySet<>();
        initEventType();
    }

    /**
     * 初始化
     */
    public abstract void initEventType();

    @Override
    public void register(EventType eventType) {
        this.set.add(eventType);
    }

    @Override
    public boolean containEventType(EventType eventType) {
        return set.contains(eventType);
    }

    @Override
    public void fireEvent(IEvent event) {
        event.call();
    }

    public Set<EventType> getSet() {
        return set;
    }
}
