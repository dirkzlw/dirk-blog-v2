package com.zlw.blog.repository;

import com.zlw.blog.po.Comment;
import com.zlw.blog.po.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ranger
 * @create 2019-06-03 22:06
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> {

}
