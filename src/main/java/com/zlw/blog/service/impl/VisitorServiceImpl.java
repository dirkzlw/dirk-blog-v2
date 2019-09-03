package com.zlw.blog.service.impl;

import com.zlw.blog.po.Visitor;
import com.zlw.blog.repository.VisitorRepository;
import com.zlw.blog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ranger
 * @create 2019-06-05 10:17
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public Visitor findVisitorByIP(String ip) {
        return visitorRepository.findDistinctByIpAddress(ip);
    }

    @Override
    public void saveVisitor(Visitor v) {
        visitorRepository.save(v);
    }
}
