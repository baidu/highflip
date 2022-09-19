package com.baidu.highflip.core.engine.adaptor;

import com.baidu.highflip.core.entity.runtime.Party;

import java.util.Iterator;

public interface PartyAdaptor {

    Party getParty(String partyid);

    Iterator<String> listParties();

    int getCount();
}
