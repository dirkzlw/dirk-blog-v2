package com.zlw.blog.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ranger
 * @create 2019-06-05 9:29
 */
@Entity
@Table(name = "t_comment")
@Getter
@Setter
public class Comment {

    //主键id及生成策略
    @Id
    @Column(length = 10)
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
    @Column(length = 40)
    private String createTime;
    //评论的内容--限400字符
    @Column(length = 500)
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
}
