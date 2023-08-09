package com.webank.ai.fate.adaptor;

import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import com.baidu.highflip.core.exception.HighFlipException;
import com.webank.ai.fate.client.form.task.FFateTask;
import com.webank.ai.fate.client.form.task.FateTask;
import com.webank.ai.fate.context.FateContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

@Slf4j
@Getter
public class TaskAdaptor implements com.baidu.highflip.core.adaptor.TaskAdaptor {

    FateContext context;

    public TaskAdaptor(FateContext context) {
        this.context = context;
    }

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
        final List<Task> tasks =
                getContext().getClient().taskQuery(task.getBingingId())
                            .getData().stream()
                            .map(FFateTask::convertToEntity)
                            .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(tasks)) {
            throw new RuntimeException("task not found: " + task);
        }
        final String status = tasks.get(0).getStatus();
        switch (status) {
            case "success":
                return Status.SUCCEEDED;
            case "timeout":
                return Status.FAILED;
            case "failed":
                return Status.FAILED;
            default:
                return Status.UNKNOWN;
        }
    }

    @Override
    public Task controlTask(Task task, Action action, Map<String, String> config) {
        return null;
    }

    @Override
    public int getTaskCount() {
        return getContext().getClient().listTask(null, 1, Integer.MAX_VALUE).getData().getCount();
    }

    @Override
    public Task getTaskByIndex(int index, Task task) {
        log.info("get task by index:{}", index);
        return FateTask.convertToEntity(getContext().getClient().listTask(null, index+1, 1).getData().getTasks().get(0));
    }

    @Override
    public void invokeTask(Task task) {

    }

    @Override
    public int getTaskLogCount(Task task) {
        return 0;
    }

    @Override
    public Iterator<String> getTaskLog(Task task, int offset, int limit) {
        return null;
    }
}
