package com.baidu.highflip.adaptor.dumb;

import com.baidu.highflip.core.entity.runtime.Task;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class TaskAdaptor{
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
