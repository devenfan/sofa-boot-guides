package com.alipay.sofa.boot.examples.lucene.mapper;

import org.apache.lucene.document.Document;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * DefaultLuceneBeanMapper
 *
 * @author Deven
 * @version : DefaultLuceneBeanMapper, v 0.1 2020-03-08 23:50 Deven Exp$
 */
public class DefaultLuceneBeanMapper<T> implements LuceneBeanMapper<T> {

    private LuceneBeanMapperManager.Mapping mapping;

    public DefaultLuceneBeanMapper(LuceneBeanMapperManager.Mapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public T doc2bean(Document document) {
        if (document == null) {
            return null;
        }
        T t = null;
        try {
            t = (T) mapping.getDomainClazz().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalStateException("Cannot construct Domain Class: " + mapping.getDomainClazz().toString());
        }
        try {
            for (Map.Entry<Field, String> fieldStringEntry : mapping.getDomainFieldToDocFieldMap().entrySet()) {
                Field domainField = fieldStringEntry.getKey();
                String docField = fieldStringEntry.getValue();
                String docFieldValue = document.get(docField);
                domainField.set(t, docFieldValue);
            }
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot access field of Domain Class: " + mapping.getDomainClazz().toString());
        }
        return t;
    }

    @Override
    public Document bean2doc(T bean) {
        return null;
    }

}
