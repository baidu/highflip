package com.baidu.highflip.server.adapter.impl;

import com.baidu.highflip.core.adaptor.PlatformAdaptor;
import com.baidu.highflip.core.common.AdaptorPropsList;
import com.baidu.highflip.core.entity.runtime.version.CompatibleVersion;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Properties;

@Slf4j
public class PropertyPlatformAdaptor implements PlatformAdaptor {

    String company;

    String product;

    String version;


    public PropertyPlatformAdaptor(Properties props){

        this.company = props.getProperty(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_PLATFORM_COMPANY,
                AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_PLATFORM_COMPANY_DEFAULT);

        this.product = props.getProperty(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_PLATFORM_PRODUCT,
                AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_PLATFORM_PRODUCT_DEFAULT);

        this.version = props.getProperty(AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_PLATFORM_VERSION,
                AdaptorPropsList.PROPS_HIGHFLIP_ADAPTOR_PLATFORM_VERSION_DEFAULT);
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public String getProduct() {
        return product;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public List<CompatibleVersion> getCompatibleList() {
        return List.of();
    }

    @Override
    public List<String> getFeatures() {
        return List.of();
    }
}
