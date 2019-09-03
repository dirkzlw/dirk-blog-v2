package com.zlw.blog.repository.es;

import com.zlw.blog.po.es.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-10 8:33
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, Integer> {

    //去重    使用关键字
    List<EsBlog> findDistinctByBlogTitleContainingOrBlogIntroContainingOrBlogTypeContaining(String blogTitle, String blogIntro, String blogType);

}
