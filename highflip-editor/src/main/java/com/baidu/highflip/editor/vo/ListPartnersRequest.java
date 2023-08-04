package com.baidu.highflip.editor.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPartnersRequest {

    String highFlipServerIp;

    int highFlipServerPort;
}
