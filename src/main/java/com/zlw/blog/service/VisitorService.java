package com.zlw.blog.service;

import com.zlw.blog.po.Visitor;

/**
 * @author Ranger
 * @create 2019-06-03 22:08
 */
public interface VisitorService {

    Visitor findVisitorByIP(String ip);

    void saveVisitor(Visitor v);
}
