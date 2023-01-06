package com.baidu.highflip.client.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Data
@AllArgsConstructor
public class GrpcURL {

    public static final String URL_REGEX_PATTERN
            = "grpc://(?:(?<token>\\S+)@)?(?<host>[^\\:]+)(?:\\:(?<port>\\d+))?";

    public static final int URL_DEFAULT_PORT = 8751;

    String protocol;

    String host;

    int port;

    String user;

    String pass;

    public static GrpcURL from(String url) {

        Matcher match = Pattern.compile(URL_REGEX_PATTERN)
                .matcher(url);

        match.find();

        String sUser = null;
        String sPass = null;

        String sToken = match.group("token");
        if(sToken != null){
            String[] items = sToken.split(":", 2);
            sUser = items[0];

            if(items.length >= 2){
                sPass = items[1];
            }
        }

        String sHost = match.group("host");
        String sPort = match.group("port");
        int nPort = URL_DEFAULT_PORT;
        if (sPort != null) {
            nPort = Integer.valueOf(sPort);
        }

        return new GrpcURL("grpc", sHost, nPort, sUser, sPass);
    }
}
