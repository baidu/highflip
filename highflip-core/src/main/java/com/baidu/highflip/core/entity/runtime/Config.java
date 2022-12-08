package com.baidu.highflip.core.entity.runtime;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_config")
public class Config {

    @Id
    @Column(name = "key_name")
    String key;

    @Column(name = "value_content")
    String value;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    DateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    DateTime updateTime;
}
