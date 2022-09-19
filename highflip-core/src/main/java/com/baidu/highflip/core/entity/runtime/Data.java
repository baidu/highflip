package com.baidu.highflip.core.entity.runtime;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@lombok.Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_data")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Data {

    @Id
    @Column(name = "data_id")
    @GenericGenerator(name="id_gen", strategy="uuid")
    @GeneratedValue(generator="id_gen")
    String dataid;

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

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, Object> binding;
}
