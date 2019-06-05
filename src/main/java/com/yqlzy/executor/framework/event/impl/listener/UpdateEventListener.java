package com.yqlzy.executor.framework.event.impl.listener;

import com.yqlzy.executor.framework.common.Constants;
import com.yqlzy.executor.framework.event.AbstractEventListener;

public class UpdateEventListener extends AbstractEventListener {

    @Override
    public void initEventType() {
        this.register(Constants.EventTypeConstants.updateEventType);
    }
}
