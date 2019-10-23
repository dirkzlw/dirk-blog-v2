package com.zlw.blog.controller;

import com.zlw.blog.po.Notice;
import com.zlw.blog.service.NoticeService;
import com.zlw.blog.vo.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-23 9:05
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 跳转到编辑公告界面
     */
    @GetMapping("/to/mgn/notice")
    public String toNoticeMgn(Model model, HttpServletRequest request) {

        List<Notice> noticeList = noticeService.findNotices();
        model.addAttribute("noticeList", noticeList);
        return "mgn/notice";
    }

    /**
     * 保存公告：新增/修改
     *
     * @param noticeId  公告id
     * @param noticeMsg 公告内容
     * @return
     */
    @PostMapping("/mgn/notice/save")
    @ResponseBody
    public ResultObj saveNotice(@RequestParam(required = false) Integer noticeId,
                                String noticeMsg) {
        return noticeService.saveNotice(noticeId, noticeMsg);
    }

    /**
     * 删除公告
     * @param noticeId 公告id
     * @return
     */
    @PostMapping("/mgn/notice/del")
    @ResponseBody
    public String delNotice(Integer noticeId){

        return noticeService.delNotice(noticeId);
    }

}
