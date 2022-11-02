package com.baidu.highflip.server.engine.component;

import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.adaptor.OperatorAdaptor;
import com.baidu.highflip.core.adaptor.PartnerAdaptor;
import com.baidu.highflip.core.adaptor.PlatformAdaptor;
import com.baidu.highflip.core.adaptor.TaskAdaptor;
import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.engine.InstanceRegister;
import com.baidu.highflip.core.engine.translator.AbstractTranslator;
import com.baidu.highflip.server.respository.DataRepository;
import com.baidu.highflip.server.respository.JobRepository;
import com.baidu.highflip.server.respository.OperatorRepository;
import com.baidu.highflip.server.respository.PartnerRepository;
import com.baidu.highflip.server.respository.PlatformRepository;
import com.baidu.highflip.server.respository.TaskRepository;
import com.baidu.highflip.server.respository.UserRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HighFlipContext {

    @Autowired
    HighFlipConfiguration configuration;

    @Autowired
    InstanceRegister register;

    @Autowired
    PlatformRepository platformReps;

    @Autowired
    UserRepository userReps;

    @Autowired
    JobRepository jobReps;

    @Autowired
    TaskRepository taskReps;

    @Autowired
    DataRepository dataReps;

    @Autowired
    OperatorRepository operatorReps;

    @Autowired
    PartnerRepository partnerReps;

    public InstanceRegister getRegister() {
        return register;
    }

    public <T> T getInstance(String name) {
        return (T) getRegister()
                .getInstance(name);
    }

    // PLATFORM
    public PlatformAdaptor getPlatformAdaptor() {
        return getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_PLATFORM);
    }

    public PlatformRepository getPlatformRepository() {
        return platformReps;
    }

    // JOB
    public AbstractTranslator getJobTranslator() {
        return (AbstractTranslator) register
                .getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_TRANSLATOR);
    }

    public JobAdaptor getJobAdaptor() {
        return getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_JOB);
    }

    public JobRepository getJobRepository() {
        return jobReps;
    }

    // ADAPTOR
    public DataAdaptor getDataAdaptor() {
        return getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_DATA);
    }

    public DataRepository getDataRepository() {
        return dataReps;
    }

    // OPERATOR
    public OperatorAdaptor getOperatorAdaptor() {
        return getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_OPERATOR);
    }

    public OperatorRepository getOperatorRepository() {
        return operatorReps;
    }

    // TASK
    public TaskAdaptor getTaskAdaptor() {
        return getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_TASK);
    }

    public TaskRepository getTaskRepository() {
        return taskReps;
    }

    // PARTNER
    public PartnerAdaptor getPartnerAdaptor() {
        throw new NotYetImplementedException();
    }

    public PartnerRepository getPartnerRepository() {
        return partnerReps;
    }
}
