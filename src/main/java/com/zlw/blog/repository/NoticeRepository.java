package com.zlw.blog.repository;

import com.zlw.blog.po.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ranger
 * @create 2019-10-22 22:06
 */
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

}
