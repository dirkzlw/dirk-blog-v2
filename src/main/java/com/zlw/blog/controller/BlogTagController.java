package com.zlw.blog.controller;

import com.zlw.blog.po.BlogTag;
import com.zlw.blog.service.BlogTagService;
import com.zlw.blog.utils.UserUtils;
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
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    @GetMapping("/to/mgn/blogtag")
    public String toBlogTag(HttpServletRequest request){
        return "mgn/blogtag";
    }

    /**
     * 保存标签--新建/修改
     * @param typeId
     * @param typeName
     * @return
     */
    @PostMapping("/mgn/blogtag/save")
    @ResponseBody
    public ResultObj saveBlogTag(@RequestParam(required = false) Integer typeId,
                                 String typeName,
                                 HttpServletRequest request){
        ResultObj rtnObj = blogTagService.saveBlogTag(typeId, typeName);

        //同步application
        if("success".equals(rtnObj.getRtn())){
            syncApplication(request, blogTagService);
        }
        return rtnObj;
    }

    /**
     * 删除标签
     *
     * @param typeId 标签id
     * @return
     */
    @PostMapping("/mgn/blogtag/del")
    @ResponseBody
    public String delBlogTag(Integer typeId,
                            HttpServletRequest request) {

        String rtn = blogTagService.delBlogTag(typeId);

        //同步application
        if ("success".equals(rtn)) {
            syncApplication(request, blogTagService);
        }

        return rtn;
    }

    /**
     * 同步application
     * @param request
     * @param blogTagService
     */
    private static void syncApplication(HttpServletRequest request, BlogTagService blogTagService){
        ServletContext application = request.getServletContext();
        List<BlogTag> blogTagList = blogTagService.findAllBlogTags();
        application.setAttribute("blogTagList", blogTagList);
    }
}
