package com.yqlzy.executor.framework.event.impl.listener;

import com.yqlzy.executor.framework.update.service.UpdateService;
import com.yqlzy.executor.framework.update.thread.dispatch.DispatchThread;

public class DispatchCreateEventListener extends CreateEventListener {

    private DispatchThread dispatchThread;

    private UpdateService updateService;
    public DispatchCreateEventListener(DispatchThread dispatchThread, UpdateService updateService) {
        super();
        this.dispatchThread = dispatchThread;
        this.updateService = updateService;
    }
}
