package com.yqlzy.executor.framework.event;

import java.io.Serializable;

public class SingleEvent<ID extends Serializable> extends AbstractEvent {

    private Long shardingId;

    public SingleEvent(EventType eventType, ID eventId, EventParam... params) {
        setEventType(eventType);
        setParams(params);
        setId(eventId);
        this.shardingId = shardingId;
    }

    public Long getShardingId() {
        return shardingId;
    }

    @Override
    public void call() {

    }
}
