package com.baidu.highflip.core.entity.runtime.version;


import lombok.Data;

@Data
public class CompatibleVersion implements Comparable<PlatformVersion> {

    String company;

    String product;

    String versionBegin;

    boolean includedBegin;

    String versionEnd;

    boolean includeEnd;


    @Override
    public int compareTo(PlatformVersion platformVersion) {
        return 0;
    }
}
