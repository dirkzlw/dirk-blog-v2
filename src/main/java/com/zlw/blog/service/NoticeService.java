package com.zlw.blog.service;

import com.zlw.blog.po.Notice;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-22 19:19
 */
public interface NoticeService {
    List<Notice> findNotices();
}
