package com.zlw.blog.service;

import com.zlw.blog.po.User;

/**
 * @author Ranger
 * @create 2019-06-04 19:47
 */
public interface UserService {
    void save(User user);

    User login(String username, String password);

    User findUserById(Integer userId);

    User findUserByEmail(String email);

    String checkUserNameAndEmail(String username, String email);
}
