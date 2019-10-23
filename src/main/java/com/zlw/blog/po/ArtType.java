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
 * 文章类型
 * @author Ranger
 * @create 2019-10-23 15:47
 */
@Entity
@Table(name="t_art_type")
@Getter
@Setter
public class ArtType {
    //主键id及生成策略
    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //类型名称--不超过10字
    @Column(length = 20)
    private String typeName;

    protected ArtType() {
    }

    public ArtType(String typeName) {
        this.typeName = typeName;
    }
}
