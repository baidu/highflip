package com.baidu.highflip.core.entity.runtime;

import com.baidu.highflip.core.entity.runtime.version.CompatibleVersion;
import com.baidu.highflip.core.entity.runtime.version.PlatformVersion;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_platform")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Platform {

    @Id
    @Column(name = "platform_id", length = 36)
    @GenericGenerator(name = "id_gen", strategy = "uuid2")
    @GeneratedValue(generator = "id_gen")
    String platformId;

    @Column(name = "name")
    String name;

    @Column(name = "status")
    String status;

    @Column(name = "is_local")
    Boolean isLocal;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    LocalDateTime updateTime;

    @Column(name = "endpoint")
    String endPoint;

    @Column(name = "company")
    String company;

    @Column(name = "product")
    String product;

    @Column(name = "version")
    String version;

    @Type(type = "json")
    @Column(name = "compatibles")
    List<CompatibleVersion> compatibles;

    public PlatformVersion toPlatformVersion(){
        PlatformVersion result = new PlatformVersion();
        result.setCompany(company);
        result.setProduct(product);
        result.setVersion(version);
        return result;
    }
}
