package com.zlw.blog.repository.es;

import com.zlw.blog.po.es.EsUser;
import org.springframework.data.domain.Page;

/**
 * @author Ranger
 * @create 2019-10-29 19:48
 */
public interface EsUserDao {
    Page<EsUser> searchEsUserByPage(String ufor, Integer currentPage, Integer user_page_size);
}
