package com.baidu.highflip.core.entity.runtime;


import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_right")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Right {

    @Id
    @Column(name = "object_id")
    String objectId;

    @Column(name = "object_type")
    String objectType;

    @Column(name = "user_id")
    String userId;

    @Column(name = "action")
    String action;

    @Column(name = "permission")
    String permission;

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
