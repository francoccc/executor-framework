package com.yqlzy.executor.framework.update.service;

import com.yqlzy.executor.framework.event.impl.event.UpdateEvent;
import com.yqlzy.executor.framework.update.cache.UpdateEventCacheFactory;
import com.yqlzy.executor.framework.update.cache.UpdateEventPoolFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class UpdateEventService {

    public static UpdateEventCacheFactory updateEventCache;

    public void start() {
        int size = 1024;
        int max = 32;
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(size * max);
        genericObjectPoolConfig.setMaxIdle(size * max);
        genericObjectPoolConfig.setMinIdle(size);
        long time = 1000 * 30;
        genericObjectPoolConfig.setMaxWaitMillis(time);
        genericObjectPoolConfig.setSoftMinEvictableIdleTimeMillis(time);
        updateEventCache = new UpdateEventCacheFactory(new UpdateEventPoolFactory(), genericObjectPoolConfig);
    }

    public void stop() {
        if(updateEventCache != null) {
            updateEventCache.close();
        }
    }

    public static UpdateEvent createUpdateEvent(){
        try {
            return updateEventCache.borrowObject();
        } catch (Exception e) {

        } finally {
            return null;
        }
    }

    public static void releaseUpdateEvent(UpdateEvent updateEvent) {
        updateEventCache.returnObject(updateEvent);
    }
}
