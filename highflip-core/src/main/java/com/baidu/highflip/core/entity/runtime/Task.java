package com.baidu.highflip.core.entity.runtime;

import com.google.type.DateTime;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Task {

    String taskid;

    String jobid;

    String name;

    DateTime createTime;

    DateTime updateTime;
}
