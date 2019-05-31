package com.yqlzy.executor.framework.event;

import java.io.Serializable;

public class CycleEvent<ID extends Serializable> extends AbstractEvent<ID> {

    private boolean isVirgin;

    /**
     *
     */
    private int updateExecutorIndex;

    /**
     * 对象是否存活
     */
    private boolean updateAlive;

    public CycleEvent(){

    }

    public CycleEvent(EventType eventType, ID eventId, EventParam... params){
        setEventType(eventType);
        setParams(params);
        setId(eventId);
    }

    @Override
    public void call() {

    }

    public boolean isVirgin() {
        return isVirgin;
    }

    public void setVirgin(boolean virgin) {
        isVirgin = virgin;
    }

    public int getUpdateExecutorIndex() {
        return updateExecutorIndex;
    }

    public void setUpdateExecutorIndex(int updateExecutorIndex) {
        this.updateExecutorIndex = updateExecutorIndex;
    }

    public boolean isUpdateAlive() {
        return updateAlive;
    }

    public void setUpdateAlive(boolean updateAlive) {
        this.updateAlive = updateAlive;
    }
}
