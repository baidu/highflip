package com.baidu.highflip.core.entity.runtime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(schema = "hf_function")
public class Function {

    @Id
    String id;

    String name;

    String inputs;

    String outputs;
}
