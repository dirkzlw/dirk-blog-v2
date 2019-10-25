package com.zlw.blog.service;

import com.zlw.blog.po.BlogTag;
import com.zlw.blog.vo.ResultObj;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-23 15:50
 */
public interface BlogTagService {
    ResultObj saveBlogTag(Integer typeId, String typeName);

    List<BlogTag> findAllBlogTags();

    String delBlogTag(Integer typeId);
}
