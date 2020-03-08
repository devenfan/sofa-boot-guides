package com.alipay.sofa.boot.examples.lucene.mapper;

import com.alipay.sofa.boot.examples.lucene.LuceneDoc;
import com.alipay.sofa.boot.examples.lucene.LuceneField;
import com.alipay.sofa.boot.examples.lucene.utils.ClassScaner;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DemoBootstrap
 *
 * @author Deven
 * @version : DemoBootstrap, v 0.1 2020-03-08 22:47 Deven Exp$
 */
@Order(0)
@Service
public class LuceneBeanMapperManager implements ApplicationContextAware, InitializingBean {

    private ApplicationContext                applicationContext;

    private String[] domainScanPackages = new String[] { "com.alipay.sofa.boot.examples.lucene.domain" };

    private Map<Class<?>, Mapping> luceneMapping = Maps.newHashMap();

    public void setDomainScanPackages(String[] domainScanPackages) {
        this.domainScanPackages = domainScanPackages;
    }

    public String[] getDomainScanPackages() {
        return domainScanPackages;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Set<Class> domainClazzes = ClassScaner.scan("com.alipay.sofa.boot.examples.lucene.domain", LuceneDoc.class);
        domainClazzes.forEach(clazz -> {
            LuceneDoc luceneDoc = (LuceneDoc) clazz.getAnnotation(LuceneDoc.class);
            luceneMapping.put(clazz, new Mapping(clazz, luceneDoc.value()));
        });
    }


    static class Mapping {
        private Class<?> domainClazz;
        private String docName;
        private Map<Field, String> domainFieldToDocFieldMap = Maps.newHashMap();
        private Map<String, Field> docFieldToDomainFieldMap = Maps.newHashMap();

        public Class<?> getDomainClazz() {
            return domainClazz;
        }

        public String getDocName() {
            return docName;
        }

        public Map<Field, String> getDomainFieldToDocFieldMap() {
            return domainFieldToDocFieldMap;
        }

        public Map<String, Field> getDocFieldToDomainFieldMap() {
            return docFieldToDomainFieldMap;
        }

        public Mapping(Class<?> domainClazz, String docName) {
            this.domainClazz = domainClazz;
            this.docName = docName;
            resolveMapping();
        }

        private void resolveMapping() {
            Field[] fields = this.domainClazz.getFields();
            if(fields != null) {
                for (Field field : fields) {
                    LuceneField luceneField = field.getAnnotation(LuceneField.class);
                    if(luceneField != null) {
                        domainFieldToDocFieldMap.put(field, luceneField.value());
                        if(docFieldToDomainFieldMap.containsKey(luceneField.value())) {
                            throw new IllegalStateException("luceneField \"" + luceneField.value() + "\" has already been mapped, please check your domain class");
                        }
                        docFieldToDomainFieldMap.put(luceneField.value(), field);
                    }
                }
            }
        }
    }

}
