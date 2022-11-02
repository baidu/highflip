package com.baidu.highflip.core.adaptor;

import com.baidu.highflip.core.entity.runtime.version.CompatibleVersion;

import java.util.Iterator;

public interface PlatformAdaptor {

    String getCompany();

    String getProduct();

    String getVersion();

    Iterator<CompatibleVersion> getCompatibleList();
}
