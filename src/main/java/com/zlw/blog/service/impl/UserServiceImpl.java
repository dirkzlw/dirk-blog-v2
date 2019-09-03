package com.zlw.blog.service.impl;

import com.zlw.blog.po.User;
import com.zlw.blog.repository.UserRepository;
import com.zlw.blog.service.UserService;
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

    @Override
    public boolean checkEmailExist(String email) {
        User user = userRepository.findByEmail(email);
        if(user!=null){
            return true;
        }
        return false;
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
}
