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
    @Override
    public ResultObj saveArttype(Integer typeId, String typeName) {
        ArtType artType = new ArtType(typeName);
        try {
            artType.setId(typeId);
            artTypeRepository.save(artType);
        } catch (Exception e) {
            return new ResultObj(artType,"fail" );
        }
        return new ResultObj(artType, "success");
    }

    @Override
    public List<ArtType> findAllArtTypes() {
        return artTypeRepository.findAll();
    }
}
