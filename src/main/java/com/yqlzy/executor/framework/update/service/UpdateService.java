package com.yqlzy.executor.framework.update.service;

import com.yqlzy.executor.framework.common.Constants;
import com.yqlzy.executor.framework.common.Loggers;
import com.yqlzy.executor.framework.event.CycleEvent;
import com.yqlzy.executor.framework.event.EventParam;
import com.yqlzy.executor.framework.event.impl.event.CreateEvent;
import com.yqlzy.executor.framework.event.impl.event.FinishEvent;
import com.yqlzy.executor.framework.event.impl.event.FinishedEvent;
import com.yqlzy.executor.framework.update.entity.IUpdate;
import com.yqlzy.executor.framework.update.pool.IUpdateExecutor;
import com.yqlzy.executor.framework.update.thread.dispatch.DispatchThread;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class UpdateService <ID extends Serializable> {

    private DispatchThread dispatchThread;

    private IUpdateExecutor updateExecutor;

    private ConcurrentHashMap<ID, IUpdate> updateMap = new ConcurrentHashMap<>();

    public UpdateService(DispatchThread dispatchThread, IUpdateExecutor updateExecutor) {
        this.dispatchThread = dispatchThread;
        this.updateExecutor = updateExecutor;
    }

    public void addReadyCreateEvent(CycleEvent event) {
        EventParam[] eventParams = event.getParams();
        IUpdate iUpdate = (IUpdate) eventParams[0].getParam();
        updateMap.put((ID) event.getId(), iUpdate);
        //通知dispatchThread
        if(Loggers.gameExecutorUtil.isDebugEnabled()) {
            Loggers.gameExecutorUtil.debug("readycreate " + iUpdate.getUpdateId() + " dispatch");
        }
        CreateEvent createEvent = new CreateEvent(Constants.EventTypeConstants.createEventType, event.getId(), eventParams);
        dispatchThread.addEvent(createEvent, Constants.EventTypeConstants.createEventType);
        dispatchThread.unpark();
    }

    public void addFinishedEvent(CycleEvent event){
        FinishedEvent readFinishEvent = (FinishedEvent) event;
        EventParam[] eventParams = event.getParams();
        IUpdate  iUpdate = (IUpdate) eventParams[0].getParam();
        //只有distpatch转发结束后，才会才缓存池里销毁
        updateMap.remove(event.getId(), iUpdate);
    }

    public void stop(){
        updateExecutor.shutdown();
        dispatchThread.shutDown();
        this.updateMap.clear();
        UpdateEventCacheService.stop();
    }

    public void start(){
        UpdateEventCache.start();
        dispatchThread.startup();
        iUpdateExecutor.startup();
        dispatchThread.start();
        this.updateMap.clear();
    }

    public void notifyStart(){
        UpdateEventCacheService.start();
        iUpdateExecutor.startup();
        this.updateMap.clear();
    }

    public UpdateService(IUpdateExecutor updateExecutor) {
        this.updateExecutor = updateExecutor;
    }

    public void notifyRun(){
        dispatchThread.notifyRun();
    }
}
