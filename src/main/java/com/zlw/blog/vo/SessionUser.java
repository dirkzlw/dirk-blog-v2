package com.zlw.blog.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 用于展示在主页上的博客vo
 * @author Ranger
 * @create 2019-06-04 17:20
 */
@Getter
@Setter
public class SessionUser {

    private Integer userId;
    private String username;
    private String email;
    private String role;
    private String headImgUrl;

    protected SessionUser() {
    }

    public SessionUser(Object init){
    }

    public SessionUser(Integer userId, String username, String email, String role, String headImgUrl) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.headImgUrl = headImgUrl;
    }
}
