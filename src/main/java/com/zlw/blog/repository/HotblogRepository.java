package com.zlw.blog.repository;

import com.zlw.blog.po.HotBlog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ranger
 * @create 2019-06-06 20:09
 */
public interface HotblogRepository extends JpaRepository<HotBlog,Integer> {

    HotBlog findByBlogId(Integer blogId);

}
