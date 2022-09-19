package com.baidu.highflip.core.entity.runtime;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.NoArgsConstructor;
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
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_job")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Job {

    @Id
    @Column(name = "job_id")
    @GenericGenerator(name="id_gen", strategy="uuid")
    @GeneratedValue(generator="id_gen")
    String jobid;

    @Column(name = "name")
    String jobName;

    @Column(name = "description")
    String description;

    @CreatedDate
    @Column(name = "create_time")
    LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    LocalDateTime updateTime;

    /*
    @Type(type = "json")
    @Column(name = "graph")
    Graph graph;
    */
    @Type(type = "json")
    @Column(name = "binding")
    Map<String, String> binding;
}
