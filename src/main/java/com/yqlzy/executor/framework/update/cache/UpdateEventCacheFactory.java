package com.yqlzy.executor.framework.update.cache;

import com.yqlzy.executor.framework.event.impl.event.UpdateEvent;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class UpdateEventCacheFactory extends GenericObjectPool<UpdateEvent> {

    public UpdateEventCacheFactory(PooledObjectFactory<UpdateEvent> factory
            , GenericObjectPoolConfig config) {
        super(factory, config);
    }
}
