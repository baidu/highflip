package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Status;

import java.util.Iterator;
import java.util.Map;

public interface TaskAdaptor {

    Task updateTask(Task task);

    void deleteTask(Task task);

    boolean hasTask(Task task);

    Status getTaskStatus(Task task);

    Task controlTask(Task task, Action action, Map<String, String> config);

    int getTaskCount();

    Task getTaskByIndex(int index, Task task);

    void invokeTask(Task task);

    int getTaskLogCount(Task task);

    Iterator<String> getTaskLog(Task task, int offset, int limit);
}
