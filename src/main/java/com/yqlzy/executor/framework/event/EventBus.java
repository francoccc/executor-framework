package com.yqlzy.executor.framework.event;

import com.yqlzy.executor.framework.event.common.IEvent;
import com.yqlzy.executor.framework.event.common.IEventBus;
import com.yqlzy.executor.framework.event.common.IEventListener;

import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class EventBus implements IEventBus {

    private Map<EventType, Set<AbstractEventListener>> listenerMap;

    private Queue<IEvent> events;

    private AtomicInteger size = new AtomicInteger();
    public EventBus() {
        this.listenerMap = new ConcurrentHashMap<EventType, Set<AbstractEventListener>>();
        this.events = new ConcurrentLinkedQueue<IEvent>();
    }


    @Override
    public void addEventListener(AbstractEventListener listener) {
        Set<EventType> set = listener.getSet();
        for(EventType eventType : set) {
            if(!listenerMap.containsKey(eventType)) {
                listenerMap.put(eventType, new HashSet<>());
            }
            listenerMap.get(eventType).add(listener);
        }
    }

    @Override
    public void removeEvenetListener(AbstractEventListener listener) {
        Set<EventType> set = listener.getSet();
        for(EventType eventType : set) {
            if(!listenerMap.containsKey(eventType)) {
                continue;
            }
            listenerMap.get(eventType).remove(listener);
        }
    }

    @Override
    public void clearEventListener() {
        listenerMap.clear();
    }

    @Override
    public void addEvent(IEvent event) {
        this.events.add(event);
        size.getAndIncrement();
    }

    @Override
    public void clearEvent() {
        events.clear();
    }

    public IEvent pollEvent(){
        IEvent event = events.poll();
        if(event != null){
            size.getAndDecrement();
        }
        return event;
    }

    @Override
    public void handleEvent() {
        while (!events.isEmpty()){
            IEvent event = pollEvent();
            if(event == null){
                break;
            }
            try {
                handleSingleEvent(event);
            }catch (Exception e){

            }
        }
    }

    @Override
    public void handleSingleEvent(IEvent event) throws Exception {
        EventType eventType = event.getEventType();
        if(listenerMap.containsKey(eventType)){
            Set<AbstractEventListener> listenerSet = listenerMap.get(eventType);
            for(IEventListener eventListener : listenerSet){
                if(eventListener.containEventType(event.getEventType())) {
                    eventListener.fireEvent(event);
                }
            }
        }
    }

    @Override
    public void clear() {
        clearEvent();
        clearEventListener();
    }

    public int getEventSize() {
        return size.get();
    }
}
