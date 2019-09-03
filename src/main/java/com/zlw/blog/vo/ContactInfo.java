package com.zlw.blog.vo;

/**
 * 联系作者的用户信息
 * @author Ranger
 * @create 2019-06-07 12:17
 */
public class ContactInfo {

    private String username;
    private String email;
    private String content;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
