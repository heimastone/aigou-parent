package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;


import java.util.HashMap;
import java.util.Map;

@Service(timeout=3000)
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    public Map search(Map searchMap) {

        Map map=new HashMap();

        Query query=new SimpleQuery("*:*");
        //添加条件
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));

//       criteria= criteria.and("item_keywords").is(searchMap.get("keywords"));


        query.addCriteria(criteria);

        ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);

        map.put("rows",page.getContent());

        System.out.println("返回值："+map.toString());
        System.out.println("page："+page.toString());
        return map;

    }
}
