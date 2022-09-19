package com.baidu.highflip.core.entity.runtime;

import com.google.type.DateTime;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Data
@Entity(name = "hf_task")
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Task {

    @Id
    @Column(name = "task_id")
    @GenericGenerator(name="id_gen", strategy="uuid")
    @GeneratedValue(generator="id_gen")
    String taskid;

    @Column(name = "job_id")
    String jobid;

    @Column(name = "name")
    String name;

    @CreatedDate
    @Column(name = "create_time")
    DateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    DateTime updateTime;

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, Object> binding;
}
