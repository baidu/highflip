package com.webank.ai.fate.adaptor;

import com.baidu.highflip.core.engine.HighFlipRuntime;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Task;
import com.baidu.highflip.core.entity.runtime.basic.Action;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import com.webank.ai.fate.client.form.JobQueryResponseForm;
import com.webank.ai.fate.client.form.ResultForm;
import com.webank.ai.fate.context.FateContext;
import com.webank.ai.fate.translator.DSLTranslator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
public class JobAdaptor implements com.baidu.highflip.core.adaptor.JobAdaptor {

    FateContext context;

    public JobAdaptor(FateContext context) {
        this.context = context;
    }

    @Override
    public List<String> getFeatures() {
        return null;
    }

    @Override
    public Job createJob(Job job) {

        DSLTranslator.FateDAG dag = getContext()
                .getTranslator()
                .translate(job.getGraph());

        String bindId = getContext()
                .getClient()
                .jobSumbit(dag.getDsl(), dag.getDsl());

        job.setBingingId(bindId);
        return job;
    }

    @Override
    public Job updateJob(Job job) {
        return null;
    }

    @Override
    public boolean hasJob(Job job) {
        return false;
    }

    @Override
    public Status getJobStatus(Job job) {
        String bindId = job.getBingingId();
        ResultForm<List<JobQueryResponseForm>> result = getContext()
                .getClient()
                .jobQuery(bindId);

        String status = result.getData()
                .get(0)
                .getStatus();

        return Status.valueOf(status);
    }

    @Override
    public void deleteJob(Job job) {

    }

    @Override
    public Job controlJob(Job job, Action action) {
        switch (action) {
            case STOP:
                getContext().getClient().jobStop(job.getBingingId());
                break;
        }
        return null;
    }

    @Override
    public int getJobCount() {
        return 0;
    }

    @Override
    public Job getJobByIndex(int index, Job job) {
        return null;
    }

    @Override
    public Optional<Job> moreJob(Job job, HighFlipRuntime runtime) {
        return Optional.empty();
    }

    @Override
    public int getTaskCount(Job job) {
        return 0;
    }

    @Override
    public List<Task> getTaskList(Job job, List<Task> task) {
        return null;
    }

    @Override
    public int getJobLogCount(Job job) {
        return 0;
    }

    @Override
    public Iterator<String> getJobLog(Job job, int offset, int limit) {
        return null;
    }
}
