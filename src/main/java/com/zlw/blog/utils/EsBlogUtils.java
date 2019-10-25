package com.zlw.blog.utils;

import com.zlw.blog.po.Blog;
import com.zlw.blog.po.es.EsBlog;

/**
 * @author Ranger
 * @create 2019-06-10 9:19
 */
public class EsBlogUtils {

    public static EsBlog getEsblogByBlog(Blog blog){

        EsBlog esBlog = new EsBlog(blog.getBlogId(), blog.getBlogTitle(), null,blog.getAuthor(),blog.getCreateTime(),blog.getCoverImgUrl());

//        int blogType = blog.getBlogType();
//        switch (blogType) {
//            case 1:
//                esBlog.setBlogType("前端");
//                break;
//            case 2:
//                esBlog.setBlogType("后端");
//                break;
//            case 3:
//                esBlog.setBlogType("架构");
//                break;
//            case 4:
//                esBlog.setBlogType("Linux");
//                break;
//            case 5:
//                esBlog.setBlogType("数据库");
//                break;
//            case 6:
//                esBlog.setBlogType("编程语言");
//                break;
//            case 7:
//                esBlog.setBlogType("其他");
//                break;
//            default:
//                esBlog.setBlogType("未定义");
//                break;
//        }
        return esBlog;
    }
}
