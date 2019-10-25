package com.zlw.blog.service.impl;

import com.zlw.blog.po.Role;
import com.zlw.blog.repository.RoleRepository;
import com.zlw.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ranger
 * @create 2019-10-25 16:21
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findRoleById(Integer roleId) {
        return roleRepository.findOne(roleId);
    }
}
