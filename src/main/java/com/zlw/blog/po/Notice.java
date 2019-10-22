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
 * 公告类
 * @author Ranger
 * @create 2019-10-22 18:45
 */
@Entity
@Table(name="t_notice")
@Getter
@Setter
public class Notice {

    //主键id及生成策略
    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //公告信息--字符长度
    @Column(length = 100)
    private String message;

    protected Notice() {
    }

    public Notice(String message) {
        this.message = message;
    }
}
