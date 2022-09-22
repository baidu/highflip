package com.baidu.highflip.adaptor.dumb;

import com.baidu.highflip.core.engine.adaptor.TaskAdaptor;
import com.baidu.highflip.core.entity.runtime.Action;
import com.baidu.highflip.core.entity.runtime.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Slf4j
@Component
public class DumbTaskAdaptor implements TaskAdaptor {

    @Override
    public Task getTask(Task task) {
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
    public int getTaskLogCount() {
        return 0;
    }

    @Override
    public Iterator<String> getTaskLog(Task task, int offset, int limit) {
        return null;
    }

    @Override
    public void controlTask(Task task, Action action) {

    }
    @Override
    public void invokeTask(Task task) {

    }
}
