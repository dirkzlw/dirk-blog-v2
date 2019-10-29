package com.zlw.blog.service.es;

import com.zlw.blog.po.es.EsUser;
import org.springframework.data.domain.Page;

/**
 * @author Ranger
 * @create 2019-10-28 22:24
 */
public interface EsUserService {
    void saveEsUser(EsUser esUser);

    EsUser findEsUserById(Integer userId);

    Page<EsUser> findEsUserByPage(Integer currentPage, Integer i);

    void delEsUser(Integer userId);

    Page<EsUser> searchEsUserByPage(String ufor, Integer currentPage, Integer user_page_size);
}
