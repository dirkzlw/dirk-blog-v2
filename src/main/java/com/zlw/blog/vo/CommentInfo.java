package com.zlw.blog.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 需要展示的评论信息
 * @author Ranger
 * @create 2019-06-05 10:59
 */
@Getter
@Setter
public class CommentInfo {

    private Integer commentId;
    private Integer userId;
    private String headImgUrl;
    private String username;
    private String createTime;
    private String content;

    protected CommentInfo() {
    }

    public CommentInfo(Integer commentId,Integer userId, String headImgUrl, String username, String createTime, String content) {
        this.commentId = commentId;
        this.userId = userId;
        this.headImgUrl = headImgUrl;
        this.username = username;
        this.createTime = createTime;
        this.content = content;
    }

}
