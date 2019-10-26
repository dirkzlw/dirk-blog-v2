package com.zlw.blog.config;

import com.zlw.blog.po.Blog;
import com.zlw.blog.po.HotBlog;
import com.zlw.blog.service.BlogService;
import com.zlw.blog.service.HotBlogService;
import com.zlw.blog.utils.HotBlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private ServletContext application;

    //定时执行--每3小时执行一次
//    @Scheduled(cron = "30 * * * * ?")
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

        //将热门博客存于application
        //去掉空对象
        //去掉空对象--注意：不能foreach删除
        HotBlogUtils.dealHotBlogList(oldHotBlogList);
        application.setAttribute("hotBlogList", oldHotBlogList);

    }
}
