package com.baidu.highflip.editor.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HighFlipEditorService {

    private static final Logger LOG =
            LoggerFactory.getLogger(HighFlipEditorService.class);

    public HighFlipEditorService() {

    }

    public void login(String username, String password,
                      String serverIp, int serverPort) {
        // TODO create grpc client to login
    }

}
