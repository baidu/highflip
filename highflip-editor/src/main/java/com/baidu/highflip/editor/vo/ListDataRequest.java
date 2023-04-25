package com.baidu.highflip.editor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDataRequest {

    String partnerId;

    String highFlipServerIp;

    int highFlipServerPort;
}
