package com.zlw.blog.service;

import com.zlw.blog.po.User;

import java.util.List;

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

    String userNameReset(Integer userId, String newUsername);

    String userPwdReset(Integer userId, String oldUserpwd, String newUserpwd);

    String userEmailReset(Integer userId, String newEmail);

    String HeadUrlReset(Integer userId, String newUrl);

    List<User> findAll();

    String checkAndSave(User user);

}
