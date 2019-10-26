package com.zlw.blog.utils;

import com.zlw.blog.po.Blog;
import com.zlw.blog.vo.BlogIndex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-10 10:09
 */
public class IndexUtils {

    /**
     * 解析博客集合
     * @param blogList
     * @return
     */
    public static List<BlogIndex> getIndexList(List<Blog> blogList){
        List<BlogIndex> blogIndexList = new ArrayList<>();
        /**
         * 注意：对象里内嵌对象，解决一行两篇博文的问题
         */
        for (int i = 0; i < blogList.size(); i += 2) {
            Blog blog = blogList.get(i);
            BlogIndex blogIndex = new BlogIndex(blog.getBlogId(), blog.getCoverImgUrl(), blog.getBlogTitle(), blog.getCreateTime(), blog.getAuthor().getUsername());
            //解决单数博客数据越界
            if (i + 1 < blogList.size()) {
                Blog blog2 = blogList.get(i + 1);
                BlogIndex blogIndex2 = new BlogIndex(blog2.getBlogId(), blog2.getCoverImgUrl(), blog2.getBlogTitle(), blog2.getCreateTime(), blog2.getAuthor().getUsername());
                blogIndex.setBlogIndex(blogIndex2);
            }
            blogIndexList.add(blogIndex);
        }
        return blogIndexList;
    }

    /**
     * 解析es中博客集合
     * @param blogList
     * @return
     */
//    public static List<BlogIndex> getEsIndexList(List<EsBlog> blogList){
//        List<BlogIndex> blogIndexList = new ArrayList<>();
//        /**
//         * 注意：对象里内嵌对象，解决一行两篇博文的问题
//         */
//        for (int i = 0; i < blogList.size(); i += 2) {
//            EsBlog blog = blogList.get(i);
//            BlogIndex blogIndex = new BlogIndex(blog.getBlogId(), blog.getCoverImgUrl(), blog.getBlogTitle(), blog.getCreateTime(), blog.getAuthor());
//            //解决单数博客数据越界
//            if (i + 1 < blogList.size()) {
//                EsBlog blog2 = blogList.get(i + 1);
//                BlogIndex blogIndex2 = new BlogIndex(blog2.getBlogId(), blog2.getCoverImgUrl(), blog2.getBlogTitle(), blog2.getCreateTime(), blog2.getAuthor());
//                blogIndex.setBlogIndex(blogIndex2);
//            }
//            blogIndexList.add(blogIndex);
//        }
//        return blogIndexList;
//    }
}
