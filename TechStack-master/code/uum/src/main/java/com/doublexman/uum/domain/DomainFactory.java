package com.doublexman.uum.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class DomainFactory {

    private static final Logger logger = LoggerFactory.getLogger(DomainFactory.class);

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static <T extends AbstractEntity> T createEntity(Class<T> clazz){
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            String msg = "Domain Factory: ReflectiveOperationException" + e.getMessage();
            logger.error(msg);
            throw new RuntimeException(msg);
        }
        t.setUid(getUUID());
        return t;
    }
}
