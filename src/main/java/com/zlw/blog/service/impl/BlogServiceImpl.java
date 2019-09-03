package com.zlw.blog.service.impl;

import com.zlw.blog.po.Blog;
import com.zlw.blog.repository.BlogRepository;
import com.zlw.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param blog
     */
    @Override
    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    /**
     * 根据id查询博客
     * @param blogId
     * @return
     */
    @Override
    public Blog findBlogByID(Integer blogId) {
        return blogRepository.findOne(blogId);
    }

    /**
     * 查询所有博客
     * @return
     */
    @Override
    public List<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

    /**
     * 根据id删除博客
     * @param blogId
     */
    @Override
    public void delBlogById(Integer blogId) {
        blogRepository.delete(blogId);
    }

    /**
     * 点赞
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
        }catch (Exception e){
            return "updateFile";
        }

    }

    /**
     * 博客访问量+1
     * @param blogId
     */
    @Override
    public void addViewNum(Integer blogId) {
        Blog blog = blogRepository.findOne(blogId);
        Integer viewNum = blog.getViewNum();
        blog.setViewNum(viewNum+1);
        blogRepository.save(blog);
    }

    /**
     * 查询人们文章
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
     * 查询所有博客
     * @return
     */
    @Override
    public List<Blog> findAllBlog() {
        return blogRepository.findAll();
    }

    /**
     * 根据标签查询博客
     * @param blogType
     * @return
     */
    @Override
    public List<Blog> findBlogByBlogType(Integer blogType) {
        return blogRepository.findDistinctByBlogType(blogType);
    }

}
