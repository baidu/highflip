package com.baidu.highflip.server.engine;

import com.baidu.highflip.core.engine.AdaptorContext;
import com.baidu.highflip.core.common.InstanceNameList;
import com.baidu.highflip.core.adaptor.AlgorithmAdaptor;
import com.baidu.highflip.core.adaptor.DataAdaptor;
import com.baidu.highflip.core.adaptor.JobAdaptor;
import com.baidu.highflip.core.adaptor.PartnerAdaptor;
import com.baidu.highflip.core.adaptor.PlatformAdaptor;
import com.baidu.highflip.core.adaptor.TaskAdaptor;
import com.baidu.highflip.core.engine.translator.AbstractTranslator;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Component;

@Component
public class HighFlipContext {

    AdaptorContext context;


    public AdaptorContext getContext(){
        return context;
    }

    public AbstractTranslator getJobTranslator(){
        return (AbstractTranslator) getContext()
                .getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_TRANSLATOR);
    }

    public PlatformAdaptor getPlatformAdaptor(){
        throw new NotYetImplementedException();
    }

    public DataAdaptor getDataAdaptor(){
        throw new NotYetImplementedException();
    }

    public AlgorithmAdaptor getFunctionAdaptor(){
        throw new NotYetImplementedException();
    }

    public JobAdaptor getJobAdaptor(){
        return (JobAdaptor) getContext()
                .getInstance(InstanceNameList.HIGHFLIP_ADAPTOR_JOB);
    }

    public TaskAdaptor getTaskAdaptor(){
        throw new NotYetImplementedException();
    }

    public PartnerAdaptor getPartnerAdaptor(){
        throw new NotYetImplementedException();
    }
}
