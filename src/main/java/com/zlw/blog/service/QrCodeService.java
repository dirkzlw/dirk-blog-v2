package com.zlw.blog.service;

import com.zlw.blog.po.QrCode;
import com.zlw.blog.vo.ResultObj;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ranger
 * @create 2019-10-27 10:58
 */
public interface QrCodeService {
    QrCode findQrCode();

    ResultObj<QrCode> saveQrCode(Integer qrId, String qrImg);
}
