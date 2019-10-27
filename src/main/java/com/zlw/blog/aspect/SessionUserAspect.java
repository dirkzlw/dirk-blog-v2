//package com.zlw.blog.aspect;
//
//import com.zlw.blog.vo.SessionUser;
//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.servlet.http.HttpSession;
//
///**
// * @author Ranger
// * @create 2019-10-26 19:01
// */
//@Aspect
//public class SessionUserAspect {
//
//    @Autowired
//    private HttpServletRequest request;
//
//    //定义通知
//    @Before(value = "execution(public * com.zlw.blog.controller.MainController.toI(..))")
//    public void checkSessionUser(){
//
//    }
//}
