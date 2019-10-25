package com.zlw.blog.service.impl;

import com.zlw.blog.po.BlogTag;
import com.zlw.blog.repository.BlogTagRepository;
import com.zlw.blog.service.BlogTagService;
import com.zlw.blog.vo.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-23 15:50
 */
@Service
public class BlogTagServiceImpl implements BlogTagService {
    @Autowired
    private BlogTagRepository blogTagRepository;

    /**
     * 查询所有标签
     *
     * @return
     */
    @Override
    public List<BlogTag> findAllBlogTags() {
        return blogTagRepository.findAll();
    }

    /**
     * 保存标签 -- 新增/修改
     *
     * @param typeId   标签id
     * @param typeName 标签名称
     * @return
     */
    @Override
    public ResultObj saveBlogTag(Integer typeId, String typeName) {
        BlogTag blogTag = new BlogTag(typeName);
        try {
            blogTag.setId(typeId);
            blogTagRepository.save(blogTag);
        } catch (Exception e) {
            return new ResultObj(blogTag, "fail");
        }
        return new ResultObj(blogTag, "success");
    }

    /**
     * 删除标签
     *
     * @param typeId 标签id
     * @return
     */
    @Override
    public String delBlogTag(Integer typeId) {
        try {
            blogTagRepository.delete(typeId);
        }catch (Exception e){
            return "fail";
        }
        return "success";
    }
}
