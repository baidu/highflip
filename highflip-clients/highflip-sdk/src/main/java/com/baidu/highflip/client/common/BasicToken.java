package com.baidu.highflip.client.common;

import com.google.common.base.Strings;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import lombok.Data;

import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.Executor;

@Data
public class BasicToken extends CallCredentials {

    public static final String BASIC_PREFIX = "Basic";

    public static final String AUTHORIZATION_METADATA_KEY = "Authorization"
            + Metadata.BINARY_HEADER_SUFFIX;

    private String value;

    private String user;

    private String token;

    public static Optional<BasicToken> of(String user, String token){
        if(!Strings.isNullOrEmpty(user) && !Strings.isNullOrEmpty(token)){
            return Optional.of(new BasicToken(user, token));
        } else {
            return Optional.empty();
        }
    }

    public BasicToken(String user, String token) {
        this.value = toAuthValue(user, token);
        this.user = user;
        this.token = token;
    }

    public static String toAuthValue(String user, String token){
        String value = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", user, token).getBytes());

        return String.format("%s %s", BASIC_PREFIX, value);
    }

    @Override
    public void applyRequestMetadata(
            RequestInfo requestInfo,
            Executor executor,
            MetadataApplier metadataApplier) {

        executor.execute(() -> {
            try {
                Metadata headers = new Metadata();
                headers.put(Metadata.Key.of(AUTHORIZATION_METADATA_KEY, Metadata.BINARY_BYTE_MARSHALLER),
                        this.getValue().getBytes());
                metadataApplier.apply(headers);
            } catch (Throwable e) {
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {
        // noop
    }
}
