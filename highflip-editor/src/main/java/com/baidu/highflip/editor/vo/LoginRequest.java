package com.baidu.highflip.editor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;

    private String password;

    private String serverIp;

    private int serverPort;
}
