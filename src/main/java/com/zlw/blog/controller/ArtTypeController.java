package com.zlw.blog.controller;

import com.zlw.blog.po.ArtType;
import com.zlw.blog.po.Notice;
import com.zlw.blog.service.ArtTypeService;
import com.zlw.blog.service.NoticeService;
import com.zlw.blog.vo.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Ranger
 * @create 2019-10-23 15:52
 */
@Controller
public class ArtTypeController {

    @Autowired
    private ArtTypeService artTypeService;

    @GetMapping("/to/mgn/arttype")
    public String toArtType(){
        return "mgn/arttype";
    }

    /**
     * 保存标签--新建/修改
     * @param typeId
     * @param typeName
     * @return
     */
    @PostMapping("/mgn/arttype/save")
    @ResponseBody
    public ResultObj saveArtType(@RequestParam(required = false) Integer typeId,
                                 String typeName,
                                 HttpServletRequest request){
        ResultObj rtnObj = artTypeService.saveArttype(typeId, typeName);

        //同步application
        if("success".equals(rtnObj.getRtn())){
            syncApplication(request, artTypeService);
        }
        return rtnObj;
    }

    /**
     * 删除标签
     *
     * @param typeId 标签id
     * @return
     */
    @PostMapping("/mgn/arttype/del")
    @ResponseBody
    public String delArtType(Integer typeId,
                            HttpServletRequest request) {

        String rtn = artTypeService.delArtType(typeId);

        //同步application
        if ("success".equals(rtn)) {
            syncApplication(request, artTypeService);
        }

        return rtn;
    }

    /**
     * 同步application
     * @param request
     * @param artTypeService
     */
    private static void syncApplication(HttpServletRequest request, ArtTypeService artTypeService){
        ServletContext application = request.getServletContext();
        List<ArtType> artTypeList = artTypeService.findAllArtTypes();
        application.setAttribute("artTypeList", artTypeList);
    }
}
