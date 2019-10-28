package com.zlw.blog.config;

import com.zlw.blog.interceptor.AdminInterceptor;
import com.zlw.blog.interceptor.ErrorInterceptor;
import com.zlw.blog.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 权限配置类
 *
 * @author Ranger
 * @create 2019-04-07 10:56
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 自定义拦截器，添加拦截路径和排除拦截路径
     * addPathPatterns():添加需要拦截的路径
     * excludePathPatterns():添加不需要拦截的路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //对非注册用户进行拦截
        UserInterceptor userRoot = new UserInterceptor();
        //对普通用户实现编辑博客，点赞，留言功能实现留言
        //,"/author/message"    先对未登录用户进行留言不拦截
        registry.addInterceptor(userRoot)
                .addPathPatterns("/blog/comment/del",
                        "/to/user/msg","/to/blog/edit","/blog/save",
                        "/blog/del","/blog/edit");                                               //AdminController
        //对管理员进行拦截
        AdminInterceptor adminRoot = new AdminInterceptor();
        //对普通用户实现编辑博客，点赞，留言功能实现留言
        //,"/author/message"    先对未登录用户进行留言不拦截
        registry.addInterceptor(adminRoot)
                .addPathPatterns("/to/mgn/center")  //管理中心
                .addPathPatterns("/to/mgn/notice",  //公告操作
                        "/mgn/notice/save", "/mgn/notice/del")
                .addPathPatterns("/to/mgn/blogtag", //标签操作
                        "/mgn/blogtag/save", "/mgn/blogtag/del")
                .addPathPatterns("/to/mgn/qrcode",  //关注操作
                        "/mgn/qrcode/save","/mgn/qrcode/del");                                          //AdminController
        //对跳转错误页面进行拦截
        ErrorInterceptor errorRoot = new ErrorInterceptor();
        registry.addInterceptor(errorRoot);
        super.addInterceptors(registry);

    }
}
