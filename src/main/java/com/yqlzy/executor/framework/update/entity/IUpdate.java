package com.yqlzy.executor.framework.update.entity;

import java.io.Serializable;

/**
 * 循环接口
 */
public interface IUpdate<ID extends Serializable> extends Serializable {

    void update();

    ID getUpdateId();

    boolean isActive();
}
