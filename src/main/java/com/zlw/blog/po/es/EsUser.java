package com.zlw.blog.po.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Ranger
 * @create 2019-10-28 10:53
 */
@Document(indexName = "blog_user_index",type = "blog_user")
@Getter
@Setter
public class EsUser {
    @Id
    private Integer userId;
    private String username;
    private String email;
    private String roleName;

    public EsUser() {
    }

    public EsUser(Integer userId, String username, String email, String roleName) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "EsUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
