package com.yqlzy.executor.framework.update.pool;

import com.lmax.disruptor.WorkerPool;
import com.yqlzy.executor.framework.common.Constants;
import com.yqlzy.executor.framework.event.EventParam;
import com.yqlzy.executor.framework.event.handler.CycleEventHandler;
import com.yqlzy.executor.framework.event.impl.event.UpdateEvent;
import com.yqlzy.executor.framework.update.service.UpdateEventService;
import com.yqlzy.executor.framework.update.thread.dispatch.DispatchThread;
import com.yqlzy.executor.framework.update.thread.dispatch.DisruptorDispatchThread;
import com.yqlzy.executor.framework.update.entity.IUpdate;

public class DisruptorExecutorService implements IUpdateExecutor {

    private WorkerPool workerPool;

    private int executorSize;
    private CycleEventHandler[] cycleEventHandlers;
    private DisruptorDispatchThread disruptorDispatchThread;
    private String poolName;

    public DisruptorExecutorService(String poolName, int executorSize) {
        this.poolName = poolName;
        this.executorSize = executorSize;
    }

    @Override
    public void executorUpdate(DispatchThread dispatchThread, IUpdate iUpdate, boolean firstFlag, int updateExecutorIndex) {
        iUpdate.update();

        EventParam<IUpdate> params = new EventParam<IUpdate>(iUpdate);
        UpdateEvent updateEvent = UpdateEventService.createUpdateEvent();
        updateEvent.setEventType(Constants.EventTypeConstants.updateEventType);
        updateEvent.setId(iUpdate.getUpdateId());
        updateEvent.setParams(params);
        updateEvent.setUpdateAlive(iUpdate.isActive());
        /*disruptorDispatchThread;*/
    }

    @Override
    public void startup() {

    }

    @Override
    public void shutdown() {

    }

    public DisruptorDispatchThread getDisruptorDispatchThread() {
        return disruptorDispatchThread;
    }

    public void setDisruptorDispatchThread(DisruptorDispatchThread disruptorDispatchThread) {
        this.disruptorDispatchThread = disruptorDispatchThread;
    }
}
