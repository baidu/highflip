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
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_operator")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Operator {

    @Id
    @Column(name = "operator_id", length = 36)
    @GenericGenerator(name = "id_gen", strategy = "com.baidu.highflip.core.utils.CustomUuidGenerator")
    @GeneratedValue(generator = "id_gen")
    String operatorId;

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

    @Type(type = "json")
    @Column(name = "inputs")
    List<String> inputs;

    @Type(type = "json")
    @Column(name = "outputs")
    String outputs;

    @Type(type = "json")
    @Column(name = "parameters")
    List<Parameter> parameters;

    @Column(name = "binding_id")
    String bindingId;

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, Object> binding;
}
