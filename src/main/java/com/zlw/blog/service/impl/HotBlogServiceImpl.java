package com.zlw.blog.service.impl;

import com.zlw.blog.po.HotBlog;
import com.zlw.blog.repository.HotblogRepository;
import com.zlw.blog.service.HotBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-06 20:11
 */
@Service
public class HotBlogServiceImpl implements HotBlogService {
    @Autowired
    private HotblogRepository hotblogRepository;

    /**
     * 保存热门文章
     * @param hotBlog
     */
    @Override
    public void saveHotBlog(HotBlog hotBlog) {
        hotblogRepository.save(hotBlog);
    }

    /**
     * 获取所有
     * @return
     */
    @Override
    public List<HotBlog> findAllHotBlog() {
        return hotblogRepository.findAll();
    }

    /**
     * 删除博客同时删除
     * @param blogId
     */
    @Override
    public void delBlogById(Integer blogId) {
        HotBlog hotBlog = hotblogRepository.findByBlogId(blogId);
        hotblogRepository.delete(hotBlog);
    }
}
