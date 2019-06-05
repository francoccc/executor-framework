package com.yqlzy.executor.framework.event.handler;

import com.lmax.disruptor.EventReleaseAware;
import com.lmax.disruptor.EventReleaser;
import com.lmax.disruptor.WorkHandler;
import com.yqlzy.executor.framework.event.CycleEvent;
import com.yqlzy.executor.framework.event.EventBus;

public class CycleEventHandler implements WorkHandler<CycleEvent>, EventReleaseAware{

    private EventReleaser eventReleaser;

    private EventBus eventBus;

    private EventReleaser eventRelease;
    
    public CycleEventHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void onEvent(CycleEvent cycleEvent) throws Exception {
        eventBus.handleSingleEvent(cycleEvent);
    }

    @Override
    public void setEventReleaser(EventReleaser eventReleaser) {
        this.eventRelease = eventReleaser;
    }
}
