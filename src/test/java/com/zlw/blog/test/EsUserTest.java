package com.zlw.blog.test;

import com.zlw.blog.po.User;
import com.zlw.blog.po.es.EsUser;
import com.zlw.blog.repository.es.EsUserRepository;
import com.zlw.blog.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-28 10:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EsUserTest {
    @Autowired
    private UserService userService;
    @Autowired
    private EsUserRepository esUserRepository;

    @Test
    public void delAll(){
        esUserRepository.deleteAll();
    }
    //将mysql中数据导入es
    @Test
    public void leadIn(){
        esUserRepository.deleteAll();
        List<User> userList = userService.findAll();
        EsUser esUser;
        for (User user : userList) {
            esUser = new EsUser(user.getUserId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRole().getRoleName(),
                    user.getStatus());
            esUserRepository.save(esUser);
        }

    }
    @Test
    public void findAll(){
        Iterable<EsUser> all = esUserRepository.findAll();
        for (EsUser esUser : all) {
            System.out.println("esUser = " + esUser);
        }
    }

}
