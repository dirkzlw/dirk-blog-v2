package com.zlw.blog.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 热门文章类
 * @author Ranger
 * @create 2019-06-06 20:01
 */
@Entity
@Table(name = "t_hotBlog")
@Getter
@Setter
public class HotBlog {

    //主键id及生成策略
    @Id
    @Column(length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotBlogId;

    @Column(length = 11)
    private Integer blogId;
    @Column(length = 100)
    private String blogTitle;
    @Column(length = 11)
    private Integer viewNum;

    protected HotBlog() {
    }

    public HotBlog(Integer blogId, String blogTitle, Integer viewNum) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.viewNum = viewNum;
    }

}
