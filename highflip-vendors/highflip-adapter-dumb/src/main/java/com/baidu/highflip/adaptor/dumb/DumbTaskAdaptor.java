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

    public Task getTask(Task task) {
        return null;
    }

    public void deleteTask(Task task) {

    }

    public boolean hasTask(Task task) {
        return false;
    }

    public int getTaskLogCount() {
        return 0;
    }

    public Iterator<String> getTaskLog(Task task, int offset, int limit) {
        return null;
    }

    public void controlTask(Task task, Action action) {

    }

    public void invokeTask(Task task) {

    }
}
