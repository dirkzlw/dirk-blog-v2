package com.zlw.blog.repository;

import com.zlw.blog.po.Blog;
import com.zlw.blog.po.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-03 22:06
 */
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    /**
     * nativeQuery = true : 支持原生sql
     *               false : JPQL语句
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT * FROM t_blog ORDER BY t_blog.view_num DESC LIMIT 6")
    List<Blog> findHotBlogs();

    List<Blog> findDistinctByBlogTag(BlogTag blogType);

}
