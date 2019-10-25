package com.zlw.blog.po;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-04 19:44
 */
@Entity
@Table(name="t_user")
@Getter
@Setter
public class User {
    @Id
    @Column(length = 11)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(length = 30,unique = true)
    private String username;
    @Column(length = 40)
    private String password;
    @Column(length = 40,unique = true)
    private String email;
    //角色：1-管理员 2-用户
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
    //头像URL
    @Column(length = 100)
    private String headImgUrl;

    //评论
    @OneToMany(mappedBy = "cuser")
    private List<Comment> commentList = new ArrayList<>();

    protected User() {
    }

    public User(String username, String password, String email, Role role, String headImgUrl, List<Comment> commentList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.headImgUrl = headImgUrl;
        this.commentList = commentList;
    }
}
