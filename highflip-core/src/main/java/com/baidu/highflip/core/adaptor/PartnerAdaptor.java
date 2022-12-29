package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.Partner;

public interface PartnerAdaptor {

    Partner createPartner(Partner partner);

    Partner getPartner(Partner partner);

    void deletePartner(Partner partner);

    int getPartnerCount();

    Partner getPartnerByIndex(int index, Partner partner);
}
