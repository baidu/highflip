package com.baidu.highflip.core.entity.runtime;

import com.google.type.DateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(schema = "hf_job")
public class Job {

    @Id
    @Column(name = "id")
    String jobid;

    @Column(name = "name")
    String jobName;

    @Column(name = "description")
    String description;

    @Column(name = "create_time")
    DateTime createTime;

    @Column(name = "change_time")
    DateTime changeTime;

    Object binding;
}
