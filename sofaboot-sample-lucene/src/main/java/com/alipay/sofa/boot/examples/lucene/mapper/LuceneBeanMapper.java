package com.alipay.sofa.boot.examples.lucene.mapper;

/**
 * LuceneBeanMapper
 *
 * @author Deven
 * @version : LuceneBeanMapper, v 0.1 2020-03-08 23:49 Deven Exp$
 */
public interface LuceneBeanMapper<T> {

    T doc2bean(org.apache.lucene.document.Document document);

    org.apache.lucene.document.Document bean2doc(T bean);
}
