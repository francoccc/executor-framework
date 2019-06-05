package com.yqlzy.executor.framework.event.factory;

import com.lmax.disruptor.EventFactory;
import com.yqlzy.executor.framework.event.CycleEvent;

public class CycleDisruptorEventFactory implements EventFactory<CycleEvent> {

    @Override
    public CycleEvent newInstance() {
        return new CycleEvent();
    }
}
