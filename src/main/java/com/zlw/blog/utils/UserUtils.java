package com.zlw.blog.utils;

import com.zlw.blog.po.User;
import com.zlw.blog.vo.SessionUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ranger
 * @create 2019-10-25 16:39
 */
public class UserUtils {
    /**
     * 将user对象转为sessionUser对象
     * @param user
     * @return
     */
    public static SessionUser getSessionUser(User user){
        return new SessionUser(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getRoleName(),
                user.getHeadImgUrl());
    }

    /**
     * 从session中获取对象
     *
     * @param request     请求
     * @param sessionName key
     * @return 返回session中存储的对象
     */
    public static Object getObjectFromSession(HttpServletRequest request, String sessionName) {
        return request.getSession().getAttribute(sessionName);
    }

    /**
     * 将对象保存在Session
     *
     * @param request     请求
     * @param response    响应
     * @param obj         要保存的对象
     * @param sessionName session的key
     */
    public static void saveObjectToSession(HttpServletRequest request,
                                           HttpServletResponse response,
                                           Object obj,
                                           String sessionName) {
        //将JSESSIONID存于本地cookie中
        Cookie cookiesessionid = new Cookie("JSESSIONID", request.getSession().getId());
        cookiesessionid.setMaxAge(24 * 60 * 60);
        response.addCookie(cookiesessionid);

        //存Session
        //根据用户角色，设置用户权限
        HttpSession session = request.getSession();
        session.setAttribute(sessionName, obj);
        session.setMaxInactiveInterval(3 * 24 * 60);    //设置session生存时间
    }


    /**
     * 当session中没有sessionUser，初始化
     * @param request
     */
    public static void initSesionUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            sessionUser = new SessionUser(null);
            session.setAttribute("sessionUser", sessionUser);
        }
    }
}
