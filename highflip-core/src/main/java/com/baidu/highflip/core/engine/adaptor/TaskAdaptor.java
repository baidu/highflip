package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Action;
import com.baidu.highflip.core.entity.runtime.Task;

import java.util.Iterator;

public interface TaskAdaptor {

    //TASK
    Task getTask(Task task);

    void deleteTask(Task task);

    boolean hasTask(Task task);

    void controlTask(Task task, Action action);

    void invokeTask(Task task);

    int getTaskLogCount();

    Iterator<String> getTaskLog(Task task, int offset, int limit);
}
