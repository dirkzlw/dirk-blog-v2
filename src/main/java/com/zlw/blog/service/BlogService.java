package com.zlw.blog.service;

import com.zlw.blog.po.Blog;
import com.zlw.blog.po.BlogTag;
import com.zlw.blog.po.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-03 22:08
 */
public interface BlogService {
    void saveBlog(Blog blog);

    Blog findBlogByID(Integer blogId);

    List<Blog> findAllBlogs();

    void delBlogById(Integer blogId);

    String saveZan(Integer zanNum, Integer blogId);

    void addViewNum(Integer blogId);

    List<Blog> findHotBlogs();

    Page<Blog> findBlogByPage(Integer page, Integer size);

    Page<Blog> findBlogByPage(Integer page, Integer size, User user);

    Page<Blog> findBlogByPage(Integer page, Integer size, String fors);

    Page<Blog> findBlogByPage(Integer page, Integer size, BlogTag blogTag);

    List<Blog> findAllBlog();

    List<Blog> findBlogByBlogTag(BlogTag blogTag);
}
