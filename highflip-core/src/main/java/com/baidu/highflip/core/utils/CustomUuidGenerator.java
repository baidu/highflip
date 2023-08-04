package com.baidu.highflip.core.utils;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

public class CustomUuidGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj)
            throws HibernateException {
        Serializable id = s.getEntityPersister(null, obj).getClassMetadata()
                           .getIdentifier(obj, s);

        if (id != null && String.valueOf(id).length() > 0) {
            return id;
        } else {
            return super.generate(s, obj);
        }
    }

}
