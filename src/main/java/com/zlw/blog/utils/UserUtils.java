package com.zlw.blog.utils;

import com.zlw.blog.po.User;
import com.zlw.blog.service.UserService;
import com.zlw.blog.vo.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ranger
 * @create 2019-10-25 16:39
 */
public class UserUtils {
    public static SessionUser getSessionUser(User user){
        return new SessionUser(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getRoleName(),
                user.getHeadImgUrl());
    }
}
