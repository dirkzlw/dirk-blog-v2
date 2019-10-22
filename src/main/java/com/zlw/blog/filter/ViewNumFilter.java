package com.zlw.blog.filter;

import com.zlw.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Ranger
 * @create 2019-06-06 8:27
 */
@Component
@WebFilter(filterName = "viewNumFilter",urlPatterns = "/blog/showOne")
public class ViewNumFilter implements Filter {

    //注入blog业务层接口
    @Autowired
    private BlogService blogService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String blogId = request.getParameter("id");
//        blogService.addViewNum(Integer.parseInt(blogId));
        //放行，执行下一个过滤器
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
