package com.zlw.blog.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 游客类
 *
 * @author Ranger
 * @create 2019-06-05 10:09
 */
@Entity
@Table(name = "t_visitor")
@Getter
@Setter
public class Visitor {
    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer visitorId;
    @Column(length = 30)
    private String ipAddress;

    @OneToMany(mappedBy = "visitor")
    private List<Comment> commentList = new ArrayList<>();

    protected Visitor() {
    }

    public Visitor(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
