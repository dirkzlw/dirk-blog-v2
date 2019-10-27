package com.zlw.blog.service.impl;

import com.zlw.blog.po.QrCode;
import com.zlw.blog.repository.QrCodeRepository;
import com.zlw.blog.service.QrCodeService;
import com.zlw.blog.vo.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-27 10:58
 */
@Service
public class QrCodeServiceImpl implements QrCodeService {
    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Override
    public QrCode findQrCode() {
        List<QrCode> qrCodeList = qrCodeRepository.findAll();
        if (qrCodeList.size() == 0) {
            return new QrCode();
        }
        return qrCodeList.get(0);
    }

    @Override
    public ResultObj<QrCode> saveQrCode(Integer qrId, String qrImg) {
        QrCode qrCode = new QrCode(qrImg);

        try {
            qrCode.setId(qrId);
            qrCodeRepository.save(qrCode);
        } catch (Exception e) {
            return new ResultObj<>(qrCode, "fail");
        }
        return new ResultObj<>(qrCode, "success");
    }

}
