package com.zlw.blog.service.es.impl;

import com.zlw.blog.po.es.EsBlog;
import com.zlw.blog.repository.es.EsBlogRepository;
import com.zlw.blog.service.es.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-10 8:39
 */
@Service
public class EsBlogServiceImpl implements EsBlogService {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Override
    public List<EsBlog> findEsBlogList(String blogTitle, String blogType) {
        return esBlogRepository.findDistinctByBlogTitleContainingOrBlogTypeContaining(blogTitle, blogType);
    }

    @Override
    public void save(EsBlog esBlog) {
        esBlogRepository.save(esBlog);
    }

    @Override
    public void delBlogById(Integer id) {
        esBlogRepository.delete(id);
    }
}
