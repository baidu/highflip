package com.baidu.highflip.server.engine.adapter.dumb;

import com.baidu.highflip.core.entity.runtime.Task;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class TaskAdaptor implements com.baidu.highflip.core.engine.adaptor.TaskAdaptor {
    public Task getTask(String taskid) {
        return null;
    }

    public void deleteTask(String taskid) {

    }

    public Iterator<String> getTaskLog(String taskid) {
        return null;
    }

    public void controlTask(Task task, String action) {

    }

    public void invokeTask(String taskid) {

    }
}
