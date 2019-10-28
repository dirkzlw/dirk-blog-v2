package com.zlw.blog.service.es;

import com.zlw.blog.po.es.EsUser;

/**
 * @author Ranger
 * @create 2019-10-28 22:24
 */
public interface EsUserService {
    void saveEsUser(EsUser esUser);

    EsUser findEsUserById(Integer userId);
}
