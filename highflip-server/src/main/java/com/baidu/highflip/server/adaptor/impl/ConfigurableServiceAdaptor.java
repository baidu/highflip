package com.baidu.highflip.server.adaptor.impl;

import java.util.Properties;

import com.baidu.highflip.core.adaptor.ServiceAdaptor;
import com.baidu.highflip.core.common.AdaptorPropsList;

public class ConfigurableServiceAdaptor implements ServiceAdaptor {

    String url;

    String partyId;

    String role;

    public ConfigurableServiceAdaptor(Properties props) {

        this.url = props.getProperty(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_SERVICE_URL,
                                         AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_SERVICE_URL_DEFAULT);

        this.partyId = props.getProperty(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_SERVICE_PARTY_ID,
                                         AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_SERVICE_PARTY_ID_DEFAULT);

        this.role = props.getProperty(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_SERVICE_ROLE,
                                         AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_SERVICE_ROLE_DEFAULT);
    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getPartyId() {
        return this.partyId;
    }

    @Override
    public String getRole() {
        return this.role;
    }
}
