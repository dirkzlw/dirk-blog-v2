//package com.zlw.blog.test;
//
//import com.zlw.blog.DirkBlogV2Application;
//import com.zlw.blog.DirkBlogV2ApplicationTests;
//import com.zlw.blog.po.Blog;
//import com.zlw.blog.po.es.EsBlog;
//import com.zlw.blog.repository.es.EsBlogRepository;
//import com.zlw.blog.service.BlogService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
///**
// * @author Ranger
// * @create 2019-06-10 8:42
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = DirkBlogV2Application.class)
//public class EsBlogTest {
//
//
//    @Autowired
//    private BlogService blogService;
//    //注入es资源库
//    @Autowired
//    private EsBlogRepository esBlogRepository;
//
//    @Before
//    public void testES(){
//
//        //清楚所有数据
//        esBlogRepository.deleteAll();
//
//        List<Blog> allBlogs = blogService.findAllBlog();
//
//        EsBlog esBlog = new EsBlog(null, null, null, null,null ,null);
//
//        for (Blog blog : allBlogs) {
//            esBlog.setBlogId(blog.getBlogId());
//            esBlog.setBlogTitle(blog.getBlogTitle());
//            esBlog.setCreateTime(blog.getCreateTime());
//            esBlog.setAuthor(blog.getAuthor().getUsername());
//            esBlog.setCoverImgUrl(blog.getCoverImgUrl());
//        }
//
//    }
//    /**
//     * 检索所有博客，并打印
//     */
//    @Test
//    public void testEsQuery(){
//        List<EsBlog> blogs = esBlogRepository.findDistinctByBlogTitleContainingOrBlogTagContaining("测试",  "前端");
//        System.out.println(blogs.size());
//        for (EsBlog blog : blogs) {
//            System.out.println("blog = " + blog);
//        }
//    }
//}
