package com.baidu.highflip.server.adapter.impl;

import com.baidu.highflip.core.adaptor.PartnerAdaptor;
import com.baidu.highflip.core.entity.runtime.Partner;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConfigurablePartnerAdaptor implements PartnerAdaptor {

    static final String HIGHFLIP_PARTNER_ADAPTOR_PREFIX = "highflip.adaptor.partner";

    Properties properties;

    List<Partner> partners;

    public ConfigurablePartnerAdaptor(Properties props) {
        properties = props;

        partners = loadPartnerList();
    }

    public List<Partner> getPartners() {
        return partners;
    }

    String getProperty(int index, String name, String defaultValue) {
        String key = String.format("%s.%d.%s", HIGHFLIP_PARTNER_ADAPTOR_PREFIX, index, name);
        return properties.getProperty(key, defaultValue);
    }

    Partner loadPartner(int index) {
        Partner partner = new Partner();

        String name = getProperty(index, "name", null);
        if (name == null) {
            return null;
        }

        partner.setName(name);
        partner.setBindingId(Integer.toString(index));
        return partner;
    }

    List<Partner> loadPartnerList() {
        List<Partner> partners = new LinkedList<>();
        for (int i = 0; true; i++) {
            Partner partner = loadPartner(i);
            if (partner == null) {
                break;
            }
            partners.add(partner);
        }
        return partners;
    }


    @Override
    public Partner createPartner(Partner partner) {
        return null;
    }

    @Override
    public Partner getPartner(Partner partner) {
        String idx = partner.getPartnerId();
        return partners.get(Integer.valueOf(idx));
    }

    @Override
    public void deletePartner(Partner partner) {

    }

    @Override
    public int getPartnerCount() {
        return partners.size();
    }

    @Override
    public Partner getPartnerByIndex(int index, Partner partner) {
        Partner p = partners.get(Integer.valueOf(index));
        partner.setName(p.getName());
        return partner;
    }
}
