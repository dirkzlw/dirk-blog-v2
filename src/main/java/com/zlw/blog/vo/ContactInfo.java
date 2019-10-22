package com.zlw.blog.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 联系作者的用户信息
 * @author Ranger
 * @create 2019-06-07 12:17
 */
@Getter
@Setter
public class ContactInfo {

    private String username;
    private String email;
    private String content;

    @Override
    public String toString() {
        return "ContactInfo{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
