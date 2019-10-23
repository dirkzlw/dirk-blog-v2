package com.zlw.blog.service.impl;

import com.zlw.blog.po.Notice;
import com.zlw.blog.repository.NoticeRepository;
import com.zlw.blog.service.NoticeService;
import com.zlw.blog.vo.ResultObj;
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

    /**
     * 查询所有公告
     *
     * @return
     */
    @Override
    public List<Notice> findNotices() {
        return noticeRepository.findAll();
    }

    /**
     * 保存公告：新增/修改
     *
     * @param noticeId  公告id
     * @param noticeMsg 公告内容
     * @return
     */
    @Override
    public ResultObj saveNotice(Integer noticeId, String noticeMsg) {
        Notice notice = new Notice(noticeMsg);
        try {
            notice.setId(noticeId);
            noticeRepository.save(notice);
        } catch (Exception e) {
            return new ResultObj(notice, "fail");
        }
        return new ResultObj(notice, "success");
    }
}
