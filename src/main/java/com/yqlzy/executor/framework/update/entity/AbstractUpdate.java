package com.yqlzy.executor.framework.update.entity;


import java.io.Serializable;

public abstract class AbstractUpdate<ID extends Serializable> implements IUpdate<ID> {

    private boolean updateActive = true;
    private ID updateId;

    @Override
    public boolean isActive() {
        return this.updateActive;
    }

    public void setActive(boolean updateAlive) {
        this.updateActive = updateAlive;
    }

    @Override
    public ID getUpdateId() {
        return this.updateId;
    }

    public void setUpdateId(ID updateId) {
        this.updateId = updateId;
    }
}
