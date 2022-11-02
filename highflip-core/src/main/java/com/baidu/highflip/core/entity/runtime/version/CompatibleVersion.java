package com.baidu.highflip.core.entity.runtime.version;


import lombok.Data;

@Data
public class CompatibleVersion {

    String company;

    String product;

    String versionBegin;

    boolean includedBegin;

    String versionEnd;

    boolean includeEnd;

    public boolean isCompatible(PlatformVersion platformVersion) {
        return false;
    }
}
