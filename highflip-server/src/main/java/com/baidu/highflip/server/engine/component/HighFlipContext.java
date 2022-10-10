package com.baidu.highflip.server.engine.component;

import com.baidu.highflip.core.engine.AdaptorContext;
import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.adaptor.OperatorAdaptor;
import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.adaptor.PartnerAdaptor;
import com.baidu.highflip.core.adaptor.PlatformAdaptor;
import com.baidu.highflip.core.adaptor.TaskAdaptor;
import com.baidu.highflip.core.engine.InstanceRegister;
import com.baidu.highflip.core.engine.translator.AbstractTranslator;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HighFlipContext {

    AdaptorContext context;

    @Autowired
    HighFlipConfiguration configuration;

    @Autowired
    InstanceRegister register;


    public AdaptorContext getContext(){
        return context;
    }

    public InstanceRegister getRegister(){
        return register;
    }

    public <T> T getInstance(String name){
        return (T) getRegister()
                .getInstance(name);
    }

    public AbstractTranslator getJobTranslator(){
        return (AbstractTranslator) register
                .getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_TRANSLATOR);
    }

    public PlatformAdaptor getPlatformAdaptor(){
        throw new NotYetImplementedException();
    }

    public DataAdaptor getDataAdaptor(){
        throw new NotYetImplementedException();
    }

    public OperatorAdaptor getFunctionAdaptor(){
        throw new NotYetImplementedException();
    }

    public JobAdaptor getJobAdaptor(){
        return getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_JOB);
    }

    public TaskAdaptor getTaskAdaptor(){
        return getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_TASK);
    }

    public PartnerAdaptor getPartnerAdaptor(){
        throw new NotYetImplementedException();
    }
}
