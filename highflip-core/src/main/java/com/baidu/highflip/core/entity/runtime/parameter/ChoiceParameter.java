package com.baidu.highflip.core.entity.runtime.parameter;

import com.baidu.highflip.core.entity.runtime.Parameter;
import lombok.Data;

import java.util.List;

@Data
public class ChoiceParameter<T> extends Parameter {

    T defaultValue;

    List<T> choices;

    Integer select = 1;
}
