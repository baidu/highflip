package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Action;
import com.baidu.highflip.core.entity.runtime.Status;
import com.baidu.highflip.core.entity.runtime.Task;

import java.util.Iterator;

public interface TaskAdaptor {

    Task updateTask(Task task);

    void deleteTask(Task task);

    boolean hasTask(Task task);

    Status getTaskStatus(Task task);

    Task controlTask(Task task, Action action);

    int getTaskCount();

    Task getTaskByIndex(int index, Task task);

    void invokeTask(Task task);

    int getTaskLogCount(Task task);

    Iterator<String> getTaskLog(Task task, int offset, int limit);
}
