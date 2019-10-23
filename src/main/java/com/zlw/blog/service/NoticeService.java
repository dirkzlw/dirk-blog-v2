package com.zlw.blog.service;

import com.zlw.blog.po.Notice;
import com.zlw.blog.vo.ResultObj;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-22 19:19
 */
public interface NoticeService {
    List<Notice> findNotices();

    ResultObj saveNotice(Integer noticeId, String noticeMsg);

    String delNotice(Integer noticeId);
}
