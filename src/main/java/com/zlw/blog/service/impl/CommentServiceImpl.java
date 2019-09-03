package com.zlw.blog.service.impl;

import com.zlw.blog.po.Comment;
import com.zlw.blog.repository.CommentRepository;
import com.zlw.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ranger
 * @create 2019-06-05 10:25
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 保存博客评论
     * @param comment
     */
    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 根据id删除博客
     * @param commentId
     */
    @Override
    public void delCommentById(Integer commentId) {
        commentRepository.delete(commentId);
    }
}
