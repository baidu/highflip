package com.baidu.highflip.adaptor.demo;

import com.baidu.highflip.core.adaptor.TaskAdaptor;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Slf4j
@Component
public class DemoTaskAdaptor implements TaskAdaptor {

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public void deleteTask(Task task) {

    }
    @Override
    public boolean hasTask(Task task) {
        return false;
    }

    @Override
    public Status getTaskStatus(Task task) {
        return null;
    }

    @Override
    public int getTaskLogCount(Task task) {
        return 0;
    }

    @Override
    public Iterator<String> getTaskLog(Task task, int offset, int limit) {
        return null;
    }

    @Override
    public Task controlTask(Task task, Action action) {
        return null;
    }

    @Override
    public int getTaskCount() {
        return 0;
    }

    @Override
    public Task getTaskByIndex(int index, Task task) {
        return null;
    }

    @Override
    public void invokeTask(Task task) {

    }
}
