package com.baidu.highflip.core.entity.runtime.version;

import highflip.v1.Highflip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatformVersion implements Comparable<PlatformVersion> {

    String company;

    String product;

    String version;

    public Highflip.PlatformVersion toProto(){
        Highflip.PlatformVersion proto = Highflip.PlatformVersion.newBuilder()
                .setCompany(this.getCompany())
                .setProduct(this.getProduct())
                .setVersion(this.getVersion())
                .build();

        return proto;
    }

    public static PlatformVersion fromProto(Highflip.PlatformVersion proto){
        return new PlatformVersion(
                proto.getCompany(),
                proto.getProduct(),
                proto.getVersion());
    }

    public static List<Integer> parseVersion(String version) {
        List<Integer> vers = Arrays.stream(version.split("\\."))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return vers;
    }

    public static int compareVersion(String left, String right) {
        List<Integer> lefts = parseVersion(left);
        int leftn = lefts.size();

        List<Integer> rights = parseVersion(right);
        int rightn = rights.size();

        for (int i = 0; i < Math.max(leftn, rightn); i++) {

            Integer l = i < leftn ? lefts.get(i) : 0;
            Integer r = i < rightn ? rights.get(i) : 0;

            int rest = l.compareTo(r);
            if (rest != 0) {
                return rest;
            }
        }
        return 0;
    }

    @Override
    public int compareTo(PlatformVersion right) {
        int compComp = company.compareToIgnoreCase(right.getCompany());
        if (compComp != 0) {
            return compComp;
        }

        int prodComp = product.compareToIgnoreCase(right.getProduct());
        if (prodComp != 0) {
            return prodComp;
        }

        return compareVersion(this.version, right.getVersion());
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%s", company, product, version);
    }
}
