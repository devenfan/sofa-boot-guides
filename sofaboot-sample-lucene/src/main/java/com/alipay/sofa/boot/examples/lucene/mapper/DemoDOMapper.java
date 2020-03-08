package com.alipay.sofa.boot.examples.lucene.mapper;

import com.alipay.sofa.boot.examples.lucene.domain.DemoDO;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.springframework.stereotype.Service;

/**
 * DemoBeanMapper
 *
 * @author Deven
 * @version : DemoBeanMapper, v 0.1 2020-03-08 22:40 Deven Exp$
 */
@Service
public class DemoDOMapper {

    public DemoDO doc2do(org.apache.lucene.document.Document document) {
        if(document == null) {
            return null;
        }
        DemoDO demoDO = new DemoDO();
        demoDO.setId(document.get("id"));
        demoDO.setFirstName(document.get("firstName"));
        demoDO.setLastName(document.get("lastName"));
        demoDO.setWebsite(document.get("website"));
        return demoDO;
    }

    public org.apache.lucene.document.Document do2doc(DemoDO demoDO) {
        Document document = new Document();
        document.add(new StringField("id", demoDO.getId() , Field.Store.YES));
        document.add(new TextField("firstName", demoDO.getFirstName() , Field.Store.YES));
        document.add(new TextField("lastName", demoDO.getLastName() , Field.Store.YES));
        document.add(new TextField("website", demoDO.getWebsite() , Field.Store.YES));
        return document;
    }

}
