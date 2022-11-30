package com.baidu.highflip.server.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_configuration")
public class Configuration {

    @Id
    @Column(name = "key_name")
    String key;

    @Column(name = "value_content")
    String value;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    LocalDateTime updateTime;
}
