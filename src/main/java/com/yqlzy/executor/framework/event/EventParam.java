package com.yqlzy.executor.framework.event;

/**
 * 事件参数包装类
 * @param <T> 包装类型
 */
public class EventParam<T> {

    private T param;

    public EventParam(T p) {
        this.param = p;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}
