package com.zlw.blog.service;

import com.zlw.blog.po.Role;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-25 16:21
 */
public interface RoleService {
    Role findRoleById(Integer roleId);

    List<Role> findAllRoles();

    Role findByRoleName(String roleName);
}
