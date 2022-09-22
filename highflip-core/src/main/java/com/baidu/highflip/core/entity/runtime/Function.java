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
import java.util.List;
import java.util.Map;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_function")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Function {

    @Id
    @Column(name = "function_id", length = 36)
    @GenericGenerator(name = "id_gen", strategy = "uuid2")
    @GeneratedValue(generator = "id_gen")
    String id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    LocalDateTime updateTime;

    @Type(type = "json")
    @Column(name = "inputs")
    List<String> inputs;

    @Type(type = "json")
    @Column(name = "outputs")
    String outputs;

    @Type(type = "json")
    @Column(name = "parameters")
    List<Parameter> parameters;

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, Object> binding;
}
