package com.baidu.highflip.core.entity.runtime;

import com.baidu.highflip.core.entity.dag.Graph;
import com.baidu.highflip.core.entity.runtime.basic.Status;
import com.baidu.highflip.core.utils.IdGenerator;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.NoArgsConstructor;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_job", indexes = {
        @Index(name = "binding_id_index", columnList = "binding_id", unique = true)
})
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Job {

    @Id
    @Column(name = "job_id", length = 36)
    @GenericGenerator(name = "id_gen", strategy = "com.baidu.highflip.core.utils.CustomUuidGenerator")
    @GeneratedValue(generator = "id_gen")
    String jobId;

    @Column(name = "name")
    String jobName;

    @Column(name = "description")
    String description;

    @CreatedDate
    @Column(name = "create_time")
    DateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    DateTime updateTime;

    @Column(name = "finish_time")
    DateTime finishTime;

    @Type(type = "json")
    @Column(name = "graph", length = 10240)
    Graph graph;

    @Column(name = "status")
    Status status;

    @Column(name = "messsage")
    String message;

    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @Type(type = "json")
    @Column(name = "inputs")
    List<String> inputTasks = new LinkedList<>();

    @Type(type = "json")
    @Column(name = "outputs")
    List<String> outputTasks = new LinkedList<>();

    @Column(name = "binding_id")
    String bingingId;

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, String> binding;

    public void bindJobId() {
        this.jobId = IdGenerator.fromStrings(this.getBingingId());
    }

    public boolean isActive() {
        switch (status) {
            case APPENDING:
            case RUNNING:
            case SUCCEEDED:
                return true;
            default:
                return false;
        }
    }
}
