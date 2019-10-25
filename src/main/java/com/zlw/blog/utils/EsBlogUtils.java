package com.zlw.blog.utils;

import com.zlw.blog.po.Blog;
import com.zlw.blog.po.es.EsBlog;

/**
 * @author Ranger
 * @create 2019-06-10 9:19
 */
public class EsBlogUtils {

    public static EsBlog getEsblogByBlog(Blog blog){

        EsBlog esBlog = new EsBlog(blog.getBlogId(), blog.getBlogTitle(), blog.getBlogTag().getTypeName(),blog.getAuthor().getUsername(),blog.getCreateTime(),blog.getCoverImgUrl());

        return esBlog;
    }
}
