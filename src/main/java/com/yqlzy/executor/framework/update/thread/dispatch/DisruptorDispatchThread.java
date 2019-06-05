package com.yqlzy.executor.framework.update.thread.dispatch;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.yqlzy.executor.framework.event.CycleEvent;
import com.yqlzy.executor.framework.event.EventBus;
import com.yqlzy.executor.framework.event.EventType;
import com.yqlzy.executor.framework.event.common.IEvent;
import com.yqlzy.executor.framework.event.factory.CycleDisruptorEventFactory;
import com.yqlzy.executor.framework.update.pool.DisruptorExecutorService;
import com.yqlzy.executor.framework.update.pool.IUpdateExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class DisruptorDispatchThread extends DispatchThread {

    private EventBus eventBus;
    private DisruptorExecutorService disruptorExecutorService;
    private BlockingQueue<IEvent> blockingQueue;
    private AtomicLong total;
    private boolean running = true;

    private int cycleSleepTime;
    private long minSleepTime;

    private RingBuffer<CycleEvent> ringBuffer;
    private final int BUF_SIZE = 1024 * 64;

    public DisruptorDispatchThread(EventBus eventBus,
                                   IUpdateExecutor updateExecutor,
                                   int cycleSleepTime,
                                   long minSleepTime) {
        super(eventBus);
        this.disruptorExecutorService = (DisruptorExecutorService) updateExecutor;
        this.blockingQueue = new LinkedBlockingQueue<>(BUF_SIZE);
        this.total = new AtomicLong();
        this.cycleSleepTime = cycleSleepTime;
        this.minSleepTime = minSleepTime;
    }

    @Override
    public IUpdateExecutor getUpdateExecutor() {
        return this.disruptorExecutorService;
    }

    @Override
    public void startup() {
        initRingBuffer();
    }

    @Override
    public void shutDown() {
        super.shutDown();
        running = false;
    }

    public void addEvent(IEvent event, EventType eventType) {
        putEvent(event);
    }

    private void putEvent(IEvent event) {
        try {
            blockingQueue.put(event);
            total.getAndIncrement();
        } catch (InterruptedException e) {

        }
    }

    public void initRingBuffer() {
        ringBuffer =  RingBuffer.createSingleProducer(new CycleDisruptorEventFactory(),
                BUF_SIZE,
                new BlockingWaitStrategy());
    }

    public RingBuffer<CycleEvent> getRingBuffer() {
        return ringBuffer;
    }

    public void setRingBuffer(RingBuffer<CycleEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
}
