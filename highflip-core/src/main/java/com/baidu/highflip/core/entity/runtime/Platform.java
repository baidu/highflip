package com.baidu.highflip.core.entity.runtime;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "hf_platform")
public class Platform {

    @Id
    @Column(name = "platform_id", length = 36)
    @GenericGenerator(name="id_gen", strategy="uuid2")
    @GeneratedValue(generator="id_gen")
    String platformId;

    @CreatedDate
    @Column(name = "create_time")
    LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    LocalDateTime updateTime;

    @Column(name = "status")
    String status;

    @Column(name = "name")
    String name;

    @Column(name = "company")
    String company;

    @Column(name = "product")
    String product;

    @Column(name = "version")
    String version;
}
