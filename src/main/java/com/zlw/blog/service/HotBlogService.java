package com.zlw.blog.service;

import com.zlw.blog.po.HotBlog;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-06 20:10
 */
public interface HotBlogService {

    void saveHotBlog(HotBlog hotBlog);

    List<HotBlog> findAllHotBlog();

    void delBlogById(Integer blogId);
}
