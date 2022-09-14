package com.baidu.highflip.core.entity.runtime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Party {

    @Id
    String id;

    String name;


}
