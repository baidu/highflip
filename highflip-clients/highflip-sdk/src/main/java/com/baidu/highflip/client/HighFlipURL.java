package com.baidu.highflip.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Data
@AllArgsConstructor
public class HighFlipURL {

    public static final String URL_REGEX_PATTERN
            = "grpc://(?:(?<token>\\S+)@)?(?<host>[^\\:]+)(?:\\:(?<port>\\d+))?";

    public static final int URL_DEFAULT_PORT = 8751;

    String protocol;

    String host;

    int port;

    String token;

    public static HighFlipURL from(String url){

        Matcher match = Pattern.compile(URL_REGEX_PATTERN)
                .matcher(url);

        match.find();

        String sToken = match.group("token");
        String sHost = match.group("host");
        String sPort = match.group("port");

        int nPort = URL_DEFAULT_PORT;
        if ( sPort != null ){
            nPort = Integer.valueOf(sPort);
        }

        return new HighFlipURL("grpc", sHost, nPort, sToken);
    }
}
