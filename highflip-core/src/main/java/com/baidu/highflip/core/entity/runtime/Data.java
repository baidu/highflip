package com.baidu.highflip.core.entity.runtime;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
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

@lombok.Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_data")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Data {

    @Id
    @Column(name = "data_id", length = 36)
    @GenericGenerator(name = "id_gen", strategy = "uuid2")
    @GeneratedValue(generator = "id_gen")
    String dataId;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @CreatedDate
    @Column(name = "create_time")
    LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    LocalDateTime updateTime;

    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, Object> binding;

    @Column(name = "data_type")
    String data_type;

    @Type(type = "json")
    @Column(name = "columns")
    List<com.baidu.highflip.core.entity.runtime.Column> columns = new LinkedList<>();
}
