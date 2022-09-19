package com.baidu.highflip.core.engine;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.Data;
import com.baidu.highflip.core.entity.runtime.Function;
import com.baidu.highflip.core.entity.runtime.Job;
import com.baidu.highflip.core.entity.runtime.Party;
import com.baidu.highflip.core.entity.runtime.Task;

import java.util.Iterator;
import java.util.List;

public abstract class EngineAdaptor {

    //PLATFORM
    public abstract String getPlatform();

    public abstract List<String> listPlatform();

    //JOB
    public abstract String createJob(Graph graph);

    public abstract Job getJob(String jobid);

    public abstract void deleteJob(String jobid);

    public abstract void controlJob(String jobid, String action);

    //TASK
    public abstract Task getTask(String taskid);

    public abstract void deleteTask(String taskid);

    public abstract Iterator<String> getTaskLog(String taskid);

    public abstract void invokeTask(String taskid);

    //DATA
    public abstract Data getData(String dataid);

    public abstract Iterator<List> fetchData(String dataid, long offset, long size);

    public abstract void provideData(String dataid);


    //FUNCTION
    public abstract List<String> listFunction();

    public abstract Function getFunction(String funcid);

    //PARTY
    public abstract List<String> listParty();

    public abstract Party getParty(String partyid);
}
