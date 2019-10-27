package com.zlw.blog.controller;

import com.zlw.blog.po.Notice;
import com.zlw.blog.po.QrCode;
import com.zlw.blog.service.NoticeService;
import com.zlw.blog.service.QrCodeService;
import com.zlw.blog.utils.FastDFSUtils;
import com.zlw.blog.vo.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理二维码
 * @author Ranger
 * @create 2019-10-23 9:05
 */
@Controller
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;
    @Value("${FDFS_ADDRESS}")
    private String FDFS_ADDRESS;
    @Value("${FDFS_CLIENT_PAHT}")
    private String FDFS_CLIENT_PAHT;

    /**
     * 跳转到编辑公告界面
     */
    @GetMapping("/to/mgn/qrcode")
    public String toNoticeMgn() {
        return "mgn/qrcode";
    }

    /**
     * 保存公告：新增/修改
     *
     * @param qrId  公告id
     * @param qrImg 公告内容
     * @return
     */
    @PostMapping("/mgn/qrcode/save")
    @ResponseBody
    public ResultObj saveNotice(@RequestParam(required = false) Integer qrId,
                                MultipartFile qrImg,
                                HttpServletRequest request) {
        String qrUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, qrImg);
        ResultObj<QrCode> rtnObj = qrCodeService.saveQrCode(qrId, qrUrl);
        //同步application
        if ("success".equals(rtnObj.getRtn())) {
            syncApplication(request, qrCodeService);
        }
        return rtnObj;
    }

    /**
     * 删除公告
     *
     * @param qrId 公告id
     * @return
     */
    @PostMapping("/mgn/qrcode/del")
    @ResponseBody
    public String delNotice(Integer qrId,
                            HttpServletRequest request) {
        String rtn = qrCodeService.delQrCode(qrId);
        //同步application
        if ("success".equals(rtn)) {
            syncApplication(request, qrCodeService);
        }
        return rtn;
    }

    /**
     * 同步application
     * @param request
     * @param qrCodeService
     */
    private static void syncApplication(HttpServletRequest request,QrCodeService qrCodeService){
        ServletContext application = request.getServletContext();
        QrCode qrCode = qrCodeService.findQrCode();
        application.setAttribute("qrCode", qrCode);
    }

}
