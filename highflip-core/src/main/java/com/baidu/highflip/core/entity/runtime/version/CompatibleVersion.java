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

    boolean isCompatible(PlatformVersion version){
        if (company.compareToIgnoreCase(version.getCompany()) != 0){
            return false;
        }

        if (product.compareToIgnoreCase(version.getProduct()) != 0){
            return false;
        }

        return true;
    }
}
