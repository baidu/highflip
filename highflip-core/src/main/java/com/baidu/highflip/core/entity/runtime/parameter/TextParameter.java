package com.baidu.highflip.core.entity.runtime.parameter;

import com.baidu.highflip.core.entity.runtime.Parameter;
import lombok.Data;

@Data
public class TextParameter extends Parameter {

    String defaultValue;

    Integer limit;
}
