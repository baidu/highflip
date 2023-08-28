package com.baidu.highflip.core.entity.runtime;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_task", indexes = {
        @Index(name = "binding_id_index", columnList = "binding_id", unique = true)
})
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Task {

    @Id
    @Column(name = "task_id", length = 36)
    @GenericGenerator(name = "id_gen", strategy = "com.baidu.highflip.core.utils.CustomUuidGenerator")
    @GeneratedValue(generator = "id_gen")
    String taskid;

    /**
     * job id is not the job id in highflip but the job id in corresponding
     * federated learning service
     */
    @Column(name = "job_id")
    String jobid;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    DateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    DateTime updateTime;

    @Column(name = "finish_time")
    DateTime finishTime;

    @Column(name = "node")
    String nodeName;

    @Column(name = "status")
    String status;

    @Column(name = "messsage")
    String message;

    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @Type(type = "json")
    @Column(name = "previous")
    List<String> previous;

    @Type(type = "json")
    @Column(name = "next")
    List<String> next;

    @Column(name = "binding_id")
    String bingingId;

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, Object> binding;

    @Type(type = "json")
    @Column(name = "output_data")
    List<String> outputData;

}
