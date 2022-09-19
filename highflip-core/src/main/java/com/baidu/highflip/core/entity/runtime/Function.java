package com.baidu.highflip.core.entity.runtime;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "hf_function")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Function {

    @Id
    @Column(name = "func_id")
    @GenericGenerator(name="id_gen", strategy="uuid")
    @GeneratedValue(generator="id_gen")
    String id;

    @Column(name = "name")
    String name;

    String inputs;

    String outputs;

    @Type(type = "json")
    @Column(name = "parameters")
    List<String> parameters;

    @Type(type = "json")
    @Column(name = "binding")
    Map<String, Object> binding;
}
