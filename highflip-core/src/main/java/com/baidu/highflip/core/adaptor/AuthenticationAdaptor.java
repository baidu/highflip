package com.baidu.highflip.core.adaptor;

import java.util.Map;

public interface AuthenticationAdaptor {

    /**
     * @param context
     * @return
     */
    String sign(Map<String, Object> context);

    void verfiy(String token);

    void revoke(String token);
}
