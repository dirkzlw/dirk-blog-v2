package com.zlw.blog.po.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 通过es的博客
 * @author Ranger
 * @create 2019-04-08 19:35
 */
@Document(indexName = "blog_index",type = "blog")
@Getter
@Setter
public class EsBlog {
    /**
     * id对应blogid
     * 根据blog标题和文章类型进行筛选
     */
    @Id
    private Integer blogId;
    private String blogTitle;
    private String blogType;
    private String author;
    private String createTime;
    private String coverImgUrl;

    protected EsBlog() {
    }

    public EsBlog(Integer blogId, String blogTitle, String blogType, String author, String createTime, String coverImgUrl) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogType = blogType;
        this.author = author;
        this.createTime = createTime;
        this.coverImgUrl = coverImgUrl;
    }

    @Override
    public String toString() {
        return "EsBlog{" +
                "blogId=" + blogId +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogType='" + blogType + '\'' +
                ", author='" + author + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
