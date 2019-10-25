package com.zlw.blog.repository;

import com.zlw.blog.po.BlogTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ranger
 * @create 2019-10-23 15:49
 */
public interface BlogTagRepository extends JpaRepository<BlogTag,Integer> {
}
