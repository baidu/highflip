package com.baidu.highflip.core.entity.runtime.parameter;

import com.baidu.highflip.core.entity.runtime.Parameter;
import lombok.Data;

import java.util.Optional;

@Data
public class RangeParameter<T> extends Parameter {

    T defaultValue;

    Optional<T> step;

    Optional<T> begin;

    Boolean includeBegin;

    Optional<T>  end;

    Boolean includeEnd;
}
