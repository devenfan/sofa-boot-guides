package com.alipay.sofa.boot.examples.lucene;

import com.alibaba.fastjson.JSON;
import com.alipay.sofa.boot.examples.lucene.domain.DemoDO;
import com.alipay.sofa.boot.examples.lucene.mapper.DemoDOMapper;
import com.google.common.collect.Lists;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.TopDocs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * DemoBizService
 *
 * @author Deven
 * @version : DemoBizService, v 0.1 2020-03-08 22:21 Deven Exp$
 */
@Service
public class DemoBizService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SearcherManager searcherManager;

    @Resource
    private DemoDOMapper demoDOMapper;

    public Object searchById(String id) {

        List<DemoDO> resultList = Lists.newArrayList();

        try {
            searcherManager.maybeRefresh();

            IndexSearcher indexSearcher = searcherManager.acquire();

            //Search by ID
            TopDocs foundDocs = searchById(id, indexSearcher);
            logger.info("Total docs: " + foundDocs.totalHits);
            for (ScoreDoc sd : foundDocs.scoreDocs) {
                Document d = indexSearcher.doc(sd.doc);
                DemoDO doObj = demoDOMapper.doc2do(d);
                resultList.add(doObj);
                logger.info("doc: {}", JSON.toJSONString(doObj));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resultList;
    }

    public Object searchByFirstName(String firstName) {
        List<DemoDO> resultList = Lists.newArrayList();

        try {
            searcherManager.maybeRefresh();

            IndexSearcher indexSearcher = searcherManager.acquire();

            //Search by firstName
            TopDocs foundDocs = searchByFirstName(firstName, indexSearcher);
            logger.info("Total docs: " + foundDocs.totalHits);
            for (ScoreDoc sd : foundDocs.scoreDocs) {
                Document d = indexSearcher.doc(sd.doc);
                DemoDO doObj = demoDOMapper.doc2do(d);
                resultList.add(doObj);
                logger.info("doc: {}", JSON.toJSONString(doObj));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resultList;
    }

    private TopDocs searchByFirstName(String firstName, IndexSearcher searcher) throws Exception {
        QueryParser qp = new QueryParser("firstName", new StandardAnalyzer());
        Query firstNameQuery = qp.parse(firstName);
        TopDocs hits = searcher.search(firstNameQuery, 10);
        return hits;
    }

    private TopDocs searchById(String id, IndexSearcher searcher) throws Exception {
        QueryParser qp = new QueryParser("id", new StandardAnalyzer());
        Query idQuery = qp.parse(id);
        TopDocs hits = searcher.search(idQuery, 10);
        return hits;
    }

}
