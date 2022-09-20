package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Partner;

import java.util.Iterator;

public interface PartnerAdaptor {

    Partner getPartner(Partner partner);

    int getCount();

    Partner getPartnerByIndex(int index, Partner partner);
}
