package com.zlw.blog.listener;

import com.zlw.blog.po.BlogTag;
import com.zlw.blog.po.HotBlog;
import com.zlw.blog.po.Notice;
import com.zlw.blog.po.QrCode;
import com.zlw.blog.po.Role;
import com.zlw.blog.service.BlogTagService;
import com.zlw.blog.service.HotBlogService;
import com.zlw.blog.service.NoticeService;
import com.zlw.blog.service.QrCodeService;
import com.zlw.blog.service.RoleService;
import com.zlw.blog.utils.HotBlogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * 监听Application
 *
 * @author Ranger
 * @create 2019-10-23 15:17
 */
@Component
public class MyApplicationListener extends HttpServlet implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private HotBlogService hotBlogService;
    @Autowired
    private QrCodeService qrCodeService;
    @Autowired
    private RoleService roleService;

    /**
     * 项目完全启动后执行
     * 将公告、标签、关注存于application
     *
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //将ApplicationContext 转化为 WebApplicationContext
        WebApplicationContext webApplicationContext =
                (WebApplicationContext) contextRefreshedEvent.getApplicationContext();
        //从webApplicationContext 中获取  servletContext
        ServletContext application = webApplicationContext.getServletContext();
        //将公告存于application
        List<Notice> noticeList = noticeService.findNotices();
        application.setAttribute("noticeList", noticeList);

        //将标签存于application
        List<BlogTag> blogTagList = blogTagService.findAllBlogTags();
        application.setAttribute("blogTagList", blogTagList);

        //将热门博客存于application
        List<HotBlog> hotBlogList = hotBlogService.findAllHotBlog();
        //去掉空对象
        //去掉空对象--注意：不能foreach删除
        HotBlogUtils.dealHotBlogList(hotBlogList);
        application.setAttribute("hotBlogList", hotBlogList);

        //将关注的二维码存于application
        QrCode qrCode = qrCodeService.findQrCode();
        application.setAttribute("qrCode", qrCode);

        //将角色存于application
        List<Role> roleList = roleService.findAllRoles();
        application.setAttribute("roleList", roleList);

    }
}
