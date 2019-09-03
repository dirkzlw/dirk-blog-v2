package com.zlw.blog.service;

import com.zlw.blog.po.Comment;

/**
 * @author Ranger
 * @create 2019-06-03 22:08
 */
public interface CommentService {

    void saveComment(Comment comment);

    void delCommentById(Integer commentId);
}
