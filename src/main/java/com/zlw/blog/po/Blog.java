package com.zlw.blog.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranger
 * @create 2019-04-03 21:51
 */
@Entity
@Table(name="t_blog")
@Getter
@Setter
public class Blog implements Serializable {
    //主键id及生成策略
    @Id
    @Column(length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;
    //博客标题--限50字符
    @Column(length = 100)
    private String blogTitle;
    //博客正文
//    @Column(length = 10000)
    private String blogText;
    //博客创建时间
    @Column(length = 40)
    private String createTime;
    //文章类型:1--原创 2-转发 3-翻译
    @Column(length = 11)
    private Integer artType;
    //博客分类:1-前端 2-后端 3-架构...
    @ManyToOne
    @JoinColumn(name="blogTagId")
    private BlogTag blogTag;
    //封面的URL
    @Column(length = 100)
    private String coverImgUrl;
    //作者
    @ManyToOne
    @JoinColumn(name = "userId")
    private User author;
    //点赞的数量
    @Column(length = 11)
    private Integer zanNum;
    //博客访问量
    @Column(length = 11)
    private Integer viewNum;
    //博客评论  一对多
    @OneToMany(mappedBy = "cblog",cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    //JPA规范
    protected Blog() {
    }
    //有参构造

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogText='" + blogText + '\'' +
                ", createTime='" + createTime + '\'' +
                ", artType=" + artType +
                ", blogTag=" + blogTag +
                ", coverImgUrl='" + coverImgUrl + '\'' +
                ", author='" + author + '\'' +
                ", zanNum=" + zanNum +
                ", viewNum=" + viewNum +
                ", comments=" + comments +
                '}';
    }
}
