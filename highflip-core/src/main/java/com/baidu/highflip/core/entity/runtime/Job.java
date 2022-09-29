package com.baidu.highflip.core.entity.runtime;

import com.baidu.highflip.core.entity.dag.Graph;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_job")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Job {

    @Id
    @Column(name = "job_id", length = 36)
    @GenericGenerator(name = "id_gen", strategy = "uuid2")
    @GeneratedValue(generator = "id_gen")
    String jobId;

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

    @Column(name = "finish_time")
    LocalDateTime finishTime;

    @Type(type = "json")
    @Column(name = "graph")
    Graph graph;

    @Column(name = "status")
    Status status;

    @Column(name = "messsage")
    String message;

    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    // @OneToMany(targetEntity = Task.class, mappedBy = "jobId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // List<Task> task;

    @Type(type = "json")
    @Column(name = "inputs")
    List<String> inputTasks = new LinkedList<>();

    @Type(type = "json")
    @Column(name = "outputs")
    List<String> outputTasks = new LinkedList<>();

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, String> binding;


    public boolean isActive() {
        switch (status) {
            case APPENDING:
            case RUNNING:
            case STOPPED:
                return true;
            default:
                return false;
        }
    }

    public void generateJobId(String original){

    }

    public void generateJobId(){

    }
}
