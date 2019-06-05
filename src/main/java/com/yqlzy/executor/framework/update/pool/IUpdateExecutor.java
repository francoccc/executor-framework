package com.yqlzy.executor.framework.update.pool;

import com.yqlzy.executor.framework.update.thread.dispatch.DispatchThread;
import com.yqlzy.executor.framework.update.entity.IUpdate;

/**
 * 循环执行器
 */
public interface IUpdateExecutor {

    /**
     * 循环执行
     * @param dispatchThread
     * @param iUpdate
     * @param firstFlag
     * @param updateExecutorIndex
     */
    void executorUpdate(DispatchThread dispatchThread, IUpdate iUpdate, boolean firstFlag, int updateExecutorIndex);

    /**
     * 启动
     */
    void startup();

    /**
     * 终止
     */
    void shutdown();
}
