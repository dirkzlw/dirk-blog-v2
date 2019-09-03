package com.zlw.blog.utils;

import com.zlw.blog.po.HotBlog;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-07 9:19
 */
public class HotBlogUtils {

    /**
     * 处理热门博客集合，将长标题省略处理
     * 同时删除空博客
     * @param hotBlogList
     */
    public static void dealHotBlogList(List<HotBlog> hotBlogList){
        for (int i = 0; i < hotBlogList.size(); i++) {
            /*if (null != hotBlogList.get(i).getBlogId() && hotBlogList.get(i).getBlogTitle().length() >= 12) {
                hotBlogList.get(i).setBlogTitle(hotBlogList.get(i).getBlogTitle().substring(0, 12) + "...");
            }*/
            if (null == hotBlogList.get(i).getBlogId()) {
                hotBlogList.remove(i);
                //此处为lisit.remove(i)陷阱
                i--;
            }
        }
    }

}
