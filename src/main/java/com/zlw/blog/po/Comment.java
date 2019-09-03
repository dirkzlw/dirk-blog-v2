package com.zlw.blog.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Ranger
 * @create 2019-06-05 9:29
 */
@Entity
@Table(name = "t_comment")
public class Comment {

    //主键id及生成策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    //与博客
    @ManyToOne()
    @JoinColumn(name = "blog_id")
    private Blog cblog;
    //与用户
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User cuser;
    //与游客
    @ManyToOne()
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    private String createTime;
    //评论的内容
    private String content;

    protected Comment() {
    }

    public Comment(Blog cblog, User cuser, Visitor visitor, String createTime, String content) {
        this.cblog = cblog;
        this.cuser = cuser;
        this.visitor = visitor;
        this.createTime = createTime;
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Blog getCblog() {
        return cblog;
    }

    public void setCblog(Blog cblog) {
        this.cblog = cblog;
    }

    public User getCuser() {
        return cuser;
    }

    public void setCuser(User cuser) {
        this.cuser = cuser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
