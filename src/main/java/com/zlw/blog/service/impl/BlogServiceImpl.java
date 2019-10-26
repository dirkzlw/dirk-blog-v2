package com.zlw.blog.service.impl;

import com.zlw.blog.po.Blog;
import com.zlw.blog.po.BlogTag;
import com.zlw.blog.po.User;
import com.zlw.blog.repository.BlogRepository;
import com.zlw.blog.service.BlogService;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-03 22:08
 */
@Service
public class BlogServiceImpl implements BlogService {
    //注入blogRepository
    @Autowired
    private BlogRepository blogRepository;

    /**
     * 保存博客
     *
     * @param blog
     */
    @Override
    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    /**
     * 根据id查询博客
     *
     * @param blogId
     * @return
     */
    @Override
    public Blog findBlogByID(Integer blogId) {
        return blogRepository.findOne(blogId);
    }

    /**
     * 查询所有博客
     *
     * @return
     */
    @Override
    public List<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

    /**
     * 根据id删除博客
     *
     * @param blogId
     */
    @Override
    public void delBlogById(Integer blogId) {
        blogRepository.delete(blogId);
    }

    /**
     * 点赞
     *
     * @param zanNum
     * @param blogId
     */
    @Override
    public String saveZan(Integer zanNum, Integer blogId) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Blog blog = blogRepository.findOne(blogId);
                    blog.setZanNum(zanNum);
                    blogRepository.save(blog);
                }
            }).start();
            return "updateSuccess";
        } catch (Exception e) {
            return "updateFile";
        }

    }

    /**
     * 博客访问量+1
     *
     * @param blogId
     */
    @Override
    public void addViewNum(Integer blogId) {
        Blog blog = blogRepository.findOne(blogId);
        Integer viewNum = blog.getViewNum();
        blog.setViewNum(viewNum + 1);
        blogRepository.save(blog);
    }

    /**
     * 查询人们文章
     *
     * @return
     */
    @Override
    public List<Blog> findHotBlogs() {
        return blogRepository.findHotBlogs();
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Blog> findBlogByPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size);
        Page<Blog> result = blogRepository.findAll(pageable);
        return result;
    }
    /**
     * 根据用户分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Blog> findBlogByPage(Integer page, Integer size, User user) {

        Blog blog = new Blog();
        blog.setAuthor(user);
        Example<Blog> example = Example.of(blog);
        Pageable pageable = new PageRequest(page, size);
        Page<Blog> result = blogRepository.findAll(example, pageable);

        return result;
    }

    /**
     * 用户搜索分页查询
     * @param page
     * @param size
     * @param fors
     * @return
     */
    @Override
    public Page<Blog> findBlogByPage(Integer page, Integer size, String fors) {
        Blog blog = new Blog();
        blog.setBlogTitle(fors);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("blogTitle", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Blog> example = Example.of(blog, matcher);
        Pageable pageable = new PageRequest(page, size);
        Page<Blog> result = blogRepository.findAll(example, pageable);

        return result;
    }

    /**
     * 标签分页查询
     * @param page
     * @param size
     * @param blogTag
     * @return
     */
    @Override
    public Page<Blog> findBlogByPage(Integer page, Integer size, BlogTag blogTag) {
        Blog blog = new Blog();
        blog.setBlogTag(blogTag);
        Example<Blog> example = Example.of(blog);
        Pageable pageable = new PageRequest(page, size);
        Page<Blog> result = blogRepository.findAll(example, pageable);
        return result;
    }

    /**
     * 查询所有博客
     *
     * @return
     */
    @Override
    public List<Blog> findAllBlog() {
        return blogRepository.findAll();
    }

    /**
     * 根据标签查询博客
     *
     * @param blogTag
     * @return
     */
    @Override
    public List<Blog> findBlogByBlogTag(BlogTag blogTag) {
        return blogRepository.findDistinctByBlogTag(blogTag);
    }

}
