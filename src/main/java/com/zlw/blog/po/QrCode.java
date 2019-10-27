package com.zlw.blog.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 关注类
 * @author Ranger
 * @create 2019-10-22 18:45
 */
@Entity
@Table(name="t_qr_code")
@Getter
@Setter
public class QrCode {

    //主键id及生成策略
    @Id
    @Column(length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //关注的二维码
    @Column(length = 100)
    private String qrUrl;

    public QrCode() {
    }

    public QrCode(String qrUrl) {
        this.qrUrl = qrUrl;
    }
}
