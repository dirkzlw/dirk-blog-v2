package com.zlw.blog.repository.es.impl;

import com.zlw.blog.po.es.EsUser;
import com.zlw.blog.repository.es.EsUserDao;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

/**
 * @author Ranger
 * @create 2019-10-29 19:46
 */
@Repository
public class EsUserDaoImpl implements EsUserDao {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Page<EsUser> searchEsUserByPage(String ufor, Integer currentPage, Integer user_page_size) {
        Pageable pageable = new PageRequest(currentPage, user_page_size);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("username", "*"+ufor+"*")) //模糊查询
                .withPageable(pageable).build();
        Page<EsUser> esUserPage = elasticsearchTemplate.queryForPage(searchQuery, EsUser.class);
        return esUserPage;
    }
}
