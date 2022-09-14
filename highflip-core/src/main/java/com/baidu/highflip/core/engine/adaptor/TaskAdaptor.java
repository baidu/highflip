package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Task;

import java.util.Iterator;

public interface TaskAdaptor {

    //TASK
    Task getTask(String taskid);

    void deleteTask(String taskid);

    Iterator<String> getTaskLog(String taskid);

    void controlTask(Task task, String action);

    void invokeTask(String taskid);
}
