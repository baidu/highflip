package com.webank.ai.fate.adaptor;

import com.baidu.highflip.core.engine.HighFlipRuntime;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.DataCategory;
import com.baidu.highflip.core.entity.runtime.basic.DataMode;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import com.webank.ai.fate.client.form.ResultForm;
import com.webank.ai.fate.client.form.job.FateJob;
import com.webank.ai.fate.client.form.job.QueryJob;
import com.webank.ai.fate.client.form.task.FFateTask;
import com.webank.ai.fate.client.form.task.FateTask;
import com.webank.ai.fate.context.FateContext;
import com.webank.ai.fate.translator.DSLTranslator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class JobAdaptor implements com.baidu.highflip.core.adaptor.JobAdaptor {

    FateContext context;

    public JobAdaptor(FateContext context) {
        this.context = context;
    }

    @Override
    public List<String> getFeatures() {
        throw new UnsupportedOperationException();
    }

    @Override
    public com.baidu.highflip.core.entity.runtime.Job createJob(com.baidu.highflip.core.entity.runtime.Job job) {

        DSLTranslator.FateDAG dag = getContext()
                .getTranslator()
                .translate(job.getGraph());

        String bindId = getContext()
                .getClient()
                .jobSubmit(dag.getDsl(), dag.getConf()).getData().getJob_id();

        job.setBingingId(bindId);
        return job;
    }

    @Override
    public com.baidu.highflip.core.entity.runtime.Job updateJob(com.baidu.highflip.core.entity.runtime.Job job) {

        String bindId = job.getJobId();
        final Iterable<Task> tasks =
                getContext().getHighFlipRuntime().listTask(bindId);
        for (Task task : tasks) {
            ResultForm<List<FFateTask>> result =
                    getContext().getClient().taskQuery(task.getBingingId());
            if (CollectionUtils.isEmpty(result.getData())) {
                throw new RuntimeException("task not found, task: " + task);
            }
            final FFateTask fFateTask = result.getData().get(0);
            final String f_status = fFateTask.getF_status();
            final Status taskStatus = convertToHighFlipStatus(f_status);

            if (task.getStatus().equals(taskStatus)) {
                // task status has not changed
                continue;
            }

            if (taskStatus == Status.SUCCEEDED) {
                // register data and bind to task
                Data data = new Data();
                data.setBinding(Map.of("jobId", job.getBingingId(),
                                       "taskId", task.getTaskid(),
                                       "taskBingingId", task.getBingingId(),
                                       "componentName", task.getName(),
                                       "role",fFateTask.getF_role(),
                                       "partyId",fFateTask.getF_party_id()));
                data.setFormat(DataMode.DENSE);
                data.setCategory(DataCategory.RESULT_DATA);
                getContext().getHighFlipRuntime().registerData(data);
                log.info("data: {}", data);
                task.setOutputData(List.of(data.getDataId()));
            }
            task.setStatus(taskStatus.name());
            getContext().getHighFlipRuntime().updateTask(task);
        }
        return job;
    }

    private Status convertToHighFlipStatus(String fateStatus) {
        switch (fateStatus) {
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
    public boolean hasJob(com.baidu.highflip.core.entity.runtime.Job job) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Status getJobStatus(com.baidu.highflip.core.entity.runtime.Job job) {
        String bindId = job.getBingingId();
        ResultForm<List<QueryJob>> result = getContext()
                .getClient()
                .jobQuery(bindId);

        String status = result.getData()
                .get(0)
                .getF_status();

        if ("SUCCESS".equals(status.toUpperCase())) {
            return Status.SUCCEEDED;
        } else {
            return Status.valueOf(status.toUpperCase());
        }
    }

    @Override
    public void deleteJob(com.baidu.highflip.core.entity.runtime.Job job) {
        throw new UnsupportedOperationException();
    }

    @Override
    public com.baidu.highflip.core.entity.runtime.Job controlJob(com.baidu.highflip.core.entity.runtime.Job job, Action action) {
        switch (action) {
            case STOP:
                getContext().getClient().jobStop(job.getBingingId());
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return null;
    }

    @Override
    public int getJobCount() {
        log.info("client get job count");
        return getContext().getClient().listJob(1, Integer.MAX_VALUE).getData().getCount();
    }

    @Override
    public com.baidu.highflip.core.entity.runtime.Job getJobByIndex(int index, com.baidu.highflip.core.entity.runtime.Job job) {
        log.info("client get job by index");
        FateJob response = getContext().getClient().listJob(index + 1, 1).getData().getJobs().get(0);
        return FateJob.convertToEntity(response);
    }

    @Override
    public Optional<com.baidu.highflip.core.entity.runtime.Job> moreJob(com.baidu.highflip.core.entity.runtime.Job job, HighFlipRuntime runtime) {
        return Optional.empty();
    }

    @Override
    public int getTaskCount(com.baidu.highflip.core.entity.runtime.Job job) {
        return getContext().getClient().listTask(job.getBingingId(), 1, Integer.MAX_VALUE).getData().getCount();
    }

    @Override
    public List<Task> getTaskList(com.baidu.highflip.core.entity.runtime.Job job, List<Task> tasks) {
        List<Task> queryResult = getContext().getClient().listTask(job.getBingingId(), 1, Integer.MAX_VALUE).getData().getTasks().stream()
                .map(FateTask::convertToEntity)
                .collect(Collectors.toList());
        for (int i = 0; i < tasks.size(); i++) {
            Task queryTask = queryResult.get(i);
            Task ret = tasks.get(i);
            // task bind job id in highflip
            queryTask.setJobid(job.getJobId());
            queryTask.setTaskid(ret.getTaskid());
            BeanUtils.copyProperties(queryTask, ret);
        }
        return tasks;
    }

    @Override
    public int getJobLogCount(com.baidu.highflip.core.entity.runtime.Job job) {
        return 0;
    }

    @Override
    public Iterator<String> getJobLog(com.baidu.highflip.core.entity.runtime.Job job, int offset, int limit) {
        return null;
    }
}
