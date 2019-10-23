package com.zlw.blog.service;

import com.zlw.blog.po.ArtType;
import com.zlw.blog.vo.ResultObj;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-23 15:50
 */
public interface ArtTypeService {
    ResultObj saveArttype(Integer typeId, String typeName);

    List<ArtType> findAllArtTypes();
}
