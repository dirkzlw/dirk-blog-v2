package com.zlw.blog.config;

import com.zlw.blog.po.Blog;
import com.zlw.blog.po.HotBlog;
import com.zlw.blog.service.BlogService;
import com.zlw.blog.service.HotBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时设置热门文章
 *
 * @author Ranger
 * @create 2019-06-06 20:13
 */
@Component
public class HotBlogConfig {

    @Autowired
    private BlogService blogService;
    @Autowired
    private HotBlogService hotBlogService;

    //定时执行--没3小时执行一次
//    @Scheduled(cron = "3 * * * * ?")
    @Scheduled(cron = "0 0 */3 * * ?")
    public void setHotBlog() {

        List<HotBlog> oldHotBlogList = hotBlogService.findAllHotBlog();
        while (oldHotBlogList.size() < 6) {
            oldHotBlogList.add(new HotBlog(null, null, null));
        }

        List<Blog> hotBlogList = blogService.findHotBlogs();
        for (int i = 0; i < hotBlogList.size(); i++) {
            Blog blog = hotBlogList.get(i);
            oldHotBlogList.get(i).setBlogId(blog.getBlogId());
            oldHotBlogList.get(i).setBlogTitle(blog.getBlogTitle());
            oldHotBlogList.get(i).setViewNum(blog.getViewNum());
        }

        for (HotBlog hotBlog : oldHotBlogList) {
            hotBlogService.saveHotBlog(hotBlog);
        }
    }
}
