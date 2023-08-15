package com.baidu.highflip.core.entity.runtime;

import com.baidu.highflip.core.entity.runtime.basic.DataCategory;
import com.baidu.highflip.core.entity.runtime.basic.DataMode;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
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

@lombok.Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_data", indexes = {
        @Index(name = "binding_id_index", columnList = "binding_id", unique = true)
})
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Data {

    @Id
    @Column(name = "data_id", length = 36)
    @GenericGenerator(name = "id_gen", strategy = "com.baidu.highflip.core.utils.CustomUuidGenerator")
    @GeneratedValue(generator = "id_gen")
    String dataId;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @CreatedDate
    @Column(name = "create_time")
    DateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    DateTime updateTime;

    @Column(name = "is_deleted")
    Boolean isDeleted = false;

    @Column(name = "category")
    DataCategory category;

    @Column(name = "format")
    DataMode format;

    @Column(name = "raw_size")
    Long rawSize;

    @Type(type = "json")
    @Column(name = "dimension")
    List<Long> dimension;

    @Type(type = "json")
    @Column(name = "columns", length = 10000)
    List<com.baidu.highflip.core.entity.runtime.basic.Column> columns = new LinkedList<>();

    @Column(name = "binding_id")
    String bingingId;

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, Object> binding;
}
