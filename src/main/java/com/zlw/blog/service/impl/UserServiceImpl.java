package com.zlw.blog.service.impl;

import com.zlw.blog.po.User;
import com.zlw.blog.repository.UserRepository;
import com.zlw.blog.service.UserService;
import com.zlw.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ranger
 * @create 2019-06-04 19:47
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 保存用户
     * @param user
     */
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        return userRepository.login(username, username, password);
    }

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Override
    public User findUserById(Integer userId) {
        return userRepository.findOne(userId);
    }

    /**
     * 根据邮箱查询用户
     * @param email
     * @return
     */
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String checkUserNameAndEmail(String username, String email) {
        User user ;
        user = userRepository.findByUsername(username);
        if(user!=null){
            return "username_exist";
        }
        user = userRepository.findByEmail(email);
        if(user!=null){
            return "email_exist";
        }
        return "success";
    }

    @Override
    public String userNameReset(Integer userId, String newUsername) {
        User oldUser = userRepository.findOne(userId);
        User user1 = userRepository.findByUsername(newUsername);
        if (user1 == null) {
            oldUser.setUsername(newUsername);
            userRepository.save(oldUser);
        } else {
            return "userNameExist";
        }
        return "success";
    }

    @Override
    public String userPwdReset(Integer userId, String oldUserpwd, String newUserpwd) {
        User oldUser = userRepository.findOne(userId);
        if (oldUser.getPassword().equals(MD5Utils.md5(oldUserpwd))) {
            oldUser.setPassword(MD5Utils.md5(newUserpwd));
            userRepository.save(oldUser);
            return "success";
        } else {
            return "oldUserpwdFalse";
        }
    }

    @Override
    public String userEmailReset(Integer userId, String newEmail) {
        User oldeUser = userRepository.findOne(userId);
        User user2 = userRepository.findByEmail(newEmail);
        if (user2 == null) {
            oldeUser.setEmail(newEmail);
            userRepository.save(oldeUser);
        } else {
            return "EmailExist";
        }
        return "success";
    }

    @Override
    public String HeadUrlReset(Integer userId, String newUrl) {
        User user = userRepository.findOne(userId);
        user.setHeadImgUrl(newUrl);
        userRepository.save(user);
        return "success";
    }
}
