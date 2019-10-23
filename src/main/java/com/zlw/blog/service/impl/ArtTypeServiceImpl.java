package com.zlw.blog.service.impl;

import com.zlw.blog.po.ArtType;
import com.zlw.blog.repository.ArtTypeRepository;
import com.zlw.blog.service.ArtTypeService;
import com.zlw.blog.vo.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-23 15:50
 */
@Service
public class ArtTypeServiceImpl implements ArtTypeService {
    @Autowired
    private ArtTypeRepository artTypeRepository;

    /**
     * 查询所有标签
     *
     * @return
     */
    @Override
    public List<ArtType> findAllArtTypes() {
        return artTypeRepository.findAll();
    }

    /**
     * 保存标签 -- 新增/修改
     *
     * @param typeId   标签id
     * @param typeName 标签名称
     * @return
     */
    @Override
    public ResultObj saveArttype(Integer typeId, String typeName) {
        ArtType artType = new ArtType(typeName);
        try {
            artType.setId(typeId);
            artTypeRepository.save(artType);
        } catch (Exception e) {
            return new ResultObj(artType, "fail");
        }
        return new ResultObj(artType, "success");
    }

    /**
     * 删除标签
     *
     * @param typeId 标签id
     * @return
     */
    @Override
    public String delArtType(Integer typeId) {
        try {
            artTypeRepository.delete(typeId);
        }catch (Exception e){
            return "fail";
        }
        return "success";
    }
}
