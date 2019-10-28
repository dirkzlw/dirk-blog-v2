package com.zlw.blog.service.es.impl;

import com.zlw.blog.po.es.EsUser;
import com.zlw.blog.repository.es.EsUserRepository;
import com.zlw.blog.service.es.EsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ranger
 * @create 2019-10-28 22:24
 */
@Service
public class EsUserServiceImpl implements EsUserService {
    @Autowired
    private EsUserRepository esUserRepository;
    @Override
    public void saveEsUser(EsUser esUser) {
        esUserRepository.save(esUser);
    }

    @Override
    public EsUser findEsUserById(Integer userId) {
        return esUserRepository.findOne(userId);
    }

}
