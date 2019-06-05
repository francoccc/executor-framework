package com.yqlzy.executor.framework.update.cache;

import com.yqlzy.executor.framework.event.impl.event.UpdateEvent;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class UpdateEventPoolFactory implements PooledObjectFactory<UpdateEvent> {

    @Override
    public PooledObject<UpdateEvent> makeObject() throws Exception {
        UpdateEvent updateEvent = new UpdateEvent();
        return new DefaultPooledObject<>(updateEvent);
    }

    @Override
    public void destroyObject(PooledObject<UpdateEvent> pooledObject) throws Exception {
        UpdateEvent event = pooledObject.getObject();
        event = null;
    }

    @Override
    public boolean validateObject(PooledObject<UpdateEvent> pooledObject) {
        return true;
    }

    @Override
    public void activateObject(PooledObject<UpdateEvent> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<UpdateEvent> pooledObject) throws Exception {

    }
}
