package com.yqlzy.executor.framework.event.service;

import com.yqlzy.executor.framework.event.EventBus;
import com.yqlzy.executor.framework.event.SingleEvent;

import java.util.concurrent.BlockingDeque;

public class AsyncEventService {

    private EventBus eventBus;

    private BlockingDeque<SingleEvent> singleEventQueue;

   /* private OrderQueuePoolExecutor orderQueuePoolExecutor;*/
}
