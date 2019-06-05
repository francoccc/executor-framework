package com.yqlzy.executor.framework.update.thread.dispatch;

import com.yqlzy.executor.framework.event.EventBus;
import com.yqlzy.executor.framework.update.pool.IUpdateExecutor;


/**
 * 事件分配器
 */
public abstract class DispatchThread extends Thread {

    private EventBus eventBus;

    public DispatchThread(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void notifyRun() {
        eventBus.handleEvent();
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void shutDown() {
        eventBus.clear();
    }

    abstract public IUpdateExecutor getUpdateExecutor();

    /**
     * 启动
     */
    abstract public void startup();
}
