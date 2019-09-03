package com.zlw.blog.repository;

import com.zlw.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Ranger
 * @create 2019-06-03 22:06
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM t_user WHERE (username=? OR email=? ) AND PASSWORD=?")
    User login(String username, String email, String password);

    User findByEmail(String email);
}
