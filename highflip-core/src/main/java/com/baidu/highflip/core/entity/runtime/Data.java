package com.baidu.highflip.core.entity.runtime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "hf_data")
public class Data {

    @Id
    String dataid;

    String name;

    String description;

    Object binding;

}
