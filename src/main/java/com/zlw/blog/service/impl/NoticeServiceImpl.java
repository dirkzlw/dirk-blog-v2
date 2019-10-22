package com.zlw.blog.service.impl;

import com.zlw.blog.po.Notice;
import com.zlw.blog.repository.NoticeRepository;
import com.zlw.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-22 19:19
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public List<Notice> findNotices() {
        return noticeRepository.findAll();
    }
}
