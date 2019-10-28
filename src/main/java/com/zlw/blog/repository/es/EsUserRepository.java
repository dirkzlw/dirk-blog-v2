package com.zlw.blog.repository.es;

import com.zlw.blog.po.es.EsUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Ranger
 * @create 2019-10-28 10:57
 */
public interface EsUserRepository extends ElasticsearchRepository<EsUser,Integer> {
}
