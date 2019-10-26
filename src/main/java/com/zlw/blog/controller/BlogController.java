package com.zlw.blog.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.zlw.blog.po.Blog;
import com.zlw.blog.po.BlogTag;
import com.zlw.blog.po.Comment;
import com.zlw.blog.po.User;
import com.zlw.blog.po.Visitor;
import com.zlw.blog.service.BlogService;
import com.zlw.blog.service.CommentService;
import com.zlw.blog.service.HotBlogService;
import com.zlw.blog.service.UserService;
import com.zlw.blog.service.VisitorService;
import com.zlw.blog.utils.FastDFSUtils;
import com.zlw.blog.utils.IndexUtils;
import com.zlw.blog.utils.UserUtils;
import com.zlw.blog.vo.BlogEdit;
import com.zlw.blog.vo.BlogIndex;
import com.zlw.blog.vo.BlogInfo;
import com.zlw.blog.vo.CommentInfo;
import com.zlw.blog.vo.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-03 22:10
 */
@Controller
public class BlogController {

    //注入blogService
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private HotBlogService hotBlogService;
//    @Autowired
//    private EsBlogService esBlogService;

    @Value("${BLOG_AUTHOR}")
    private String BLOG_AUTHOR;
    @Value("${FDFS_ADDRESS}")
    private String FDFS_ADDRESS;
    @Value("${USER_HEAD_FIRST}")
    private String USER_HEAD_FIRST;
    @Value("${FDFS_CLIENT_PAHT}")
    private String FDFS_CLIENT_PAHT;
    @Value("${PAGE_SIZE}")
    private Integer PAGE_SIZE;

    /**
     * 跳转到编辑博客页面
     */
    @GetMapping("/to/blog/edit")
    public String toBlogEdit(Model model) {
        BlogEdit blogEdit = new BlogEdit(null, null, null, null, null, null);
        model.addAttribute("blog", blogEdit);
        return "blog/edit";
    }

    /**
     * 保存博客
     */
    @PostMapping("/blog/save")
    public String saveBlog(Blog blog,
                           MultipartFile coverImg,
                           Model model,
                           HttpServletRequest request) {

        //保存新博客
        if (blog.getBlogId() == null) {
            //添加作者
            SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
            User user = userService.findUserById(sessionUser.getUserId());
            blog.setAuthor(user);

            //为博客添加创作时间
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String timeStr = dateFormat.format(new Date());
            blog.setCreateTime(timeStr);
            blog.setZanNum(0);
            //访问量初始化为1,因为编辑完即访问
            blog.setViewNum(0);
            //为博客保存封面
            if (coverImg != null) {
                String coverImgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, coverImg);
                blog.setCoverImgUrl(coverImgUrl);
            }
        } else {
            Blog oldBlog = blogService.findBlogByID(blog.getBlogId());
            blog.setAuthor(oldBlog.getAuthor());
            if (StringUtils.isEmpty(coverImg.getOriginalFilename())) {
                blog.setCoverImgUrl(oldBlog.getCoverImgUrl());
            } else {
                String coverImgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, coverImg);
                blog.setCoverImgUrl(coverImgUrl);
            }
            blog.setCreateTime(oldBlog.getCreateTime());
            blog.setZanNum(oldBlog.getZanNum());
            blog.setViewNum(oldBlog.getViewNum());
        }

        //将数据保存到数据库
        blogService.saveBlog(blog);

        //将数据保存到ES
//        EsBlog esBlog = EsBlogUtils.getEsblogByBlog(blog);
//        esBlogService.save(esBlog);

        BlogInfo blogInfo = new BlogInfo(blog.getBlogId(), blog.getBlogTitle(),
                blog.getBlogText(), blog.getCreateTime(),
                blog.getArtType(), blog.getBlogTag().getTypeName(),
                blog.getCoverImgUrl(), blog.getAuthor().getUsername(),
                0, blog.getZanNum(), blog.getViewNum());

        //添加到视图
        model.addAttribute("blog", blogInfo);

        return "redirect:/blog/showOne?id=" + blog.getBlogId();
    }

    /**
     * 删除博客
     */
    @PostMapping("/blog/del")
    @ResponseBody
    public String delBlog(@RequestParam(required = false) Integer blogId) {

        try {
            blogService.delBlogById(blogId);
            //删除esblog
//            esBlogService.delBlogById(blogId);
            //删除hotblog
            hotBlogService.delBlogById(blogId);
        } catch (Exception e) {
            e.printStackTrace();
            return "delFail";
        }
        return "delSuccess";
    }

    /**
     * 搜索博客
     * @param fors 用户输入搜索
     * @param model
     * @return
     */
    @GetMapping("/blog/search/fors")
    public String searchBlog(@RequestParam(required = false) String fors,
                             Model model,Integer currentPage,
                             HttpServletRequest request) {
        UserUtils.initSesionUser(request);
//        List<EsBlog> blogList = esBlogService.findEsBlogList(fors, fors);
//        List<BlogIndex> blogIndexList = IndexUtils.getEsIndexList(blogList);
//
//        model.addAttribute("blogList", blogIndexList);

        if (currentPage == null) {
            currentPage = 0;
        }
        //默认差群起始页
        Page<Blog> pageObj = blogService.findBlogByPage(currentPage, PAGE_SIZE,fors);
        List<Blog> blogList = pageObj.getContent();
        com.zlw.blog.vo.Page page = new com.zlw.blog.vo.Page(currentPage, pageObj.getTotalPages(),blogList.size());

        List<BlogIndex> blogIndexList = IndexUtils.getIndexList(blogList);

        model.addAttribute("blogList", blogIndexList);
        model.addAttribute("page", page);
        model.addAttribute("fors", fors);

        return "search/fors";
    }

    /**
     * 根据标签查询博客
     */
    @GetMapping("/blog/search/tag")
    public String tagSearch(BlogTag blogTag,
                            Integer currentPage,
                            Model model,
                            HttpServletRequest request) {
        UserUtils.initSesionUser(request);

//        List<Blog> blogList = blogService.findBlogByBlogTag(blogTag);
//        List<BlogIndex> blogIndexList = IndexUtils.getIndexList(blogList);
//        model.addAttribute("blogList", blogIndexList);

        if (currentPage == null) {
            currentPage = 0;
        }
        //默认差群起始页
        Page<Blog> pageObj = blogService.findBlogByPage(currentPage, PAGE_SIZE,blogTag);
        List<Blog> blogList = pageObj.getContent();
        com.zlw.blog.vo.Page page = new com.zlw.blog.vo.Page(currentPage, pageObj.getTotalPages(),blogList.size());

        List<BlogIndex> blogIndexList = IndexUtils.getIndexList(blogList);

        model.addAttribute("blogList", blogIndexList);
        model.addAttribute("page", page);
        model.addAttribute("tag", blogTag.getId());

        return "search/tag";
    }

    /**
     * 查询指定用户的主页
     * @param model
     * @param ud
     * @param currentPage
     * @return
     */
    @GetMapping("/blog/search/ud")
    public String userSearch(Model model,
                             Integer ud,
                             Integer currentPage,
                             HttpServletRequest request){
        UserUtils.initSesionUser(request);
        if (currentPage == null) {
            currentPage = 0;
        }
        //默认差群起始页
        Page<Blog> pageObj = blogService.findBlogByPage(currentPage, PAGE_SIZE,userService.findUserById(ud));
        List<Blog> blogList = pageObj.getContent();
        com.zlw.blog.vo.Page page = new com.zlw.blog.vo.Page(currentPage, pageObj.getTotalPages(),blogList.size());

        List<BlogIndex> blogIndexList = IndexUtils.getIndexList(blogList);

        model.addAttribute("blogList", blogIndexList);
        model.addAttribute("page", page);
        model.addAttribute("ud", ud);

        return "search/ud";
    }

    /**
     * 跳转到博客页面，展示博客
     *
     * @return
     */
    @GetMapping("/blog/showOne")
    public String showOne(@RequestParam(value = "id", required = false) Integer blogId,
                          Model model,
                          HttpServletRequest request) {
        //初始化sessionUser
        UserUtils.initSesionUser(request);

        //查询博客
        Blog blog = blogService.findBlogByID(blogId);
        //封装博客信息对象
        BlogInfo blogInfo = new BlogInfo(blog.getBlogId(), blog.getBlogTitle(),
                blog.getBlogText(), blog.getCreateTime(),
                blog.getArtType(),
                blog.getBlogTag().getTypeName(), blog.getCoverImgUrl(), blog.getAuthor().getUsername(),
                null, blog.getZanNum(), blog.getViewNum());

        //获取评论，解析评论
        List<Comment> comments = blog.getComments();
        List<CommentInfo> commentInfoList = new ArrayList<>();
        //对象转换
        for (Comment comment : comments) {
            User cuser = comment.getCuser();
            if (cuser != null) {
                CommentInfo info = new CommentInfo(comment.getCommentId(), cuser.getUserId(), cuser.getHeadImgUrl(), cuser.getUsername(), comment.getCreateTime(), comment.getContent());
                commentInfoList.add(info);
            } else {
                Visitor visitor = comment.getVisitor();
                CommentInfo info = new CommentInfo(comment.getCommentId(), null, USER_HEAD_FIRST, "游客" + visitor.getIpAddress(), comment.getCreateTime(), comment.getContent());
                commentInfoList.add(info);
            }
        }
        model.addAttribute("commentList", commentInfoList);

        //设置评论数量
        blogInfo.setCommNum(commentInfoList.size());
        //将博客信息保存到model
        model.addAttribute("blog", blogInfo);

        return "blog/show";
    }

    /**
     * 博客评论
     */
    @PostMapping("/blog/comment")
    @ResponseBody
    public Object blogComment(@RequestParam(required = false) Integer blogId,
                              String message,
                              HttpServletRequest request) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = dateFormat.format(new Date());
        Comment comment = new Comment(null, null, null, timeStr, message);
        //从session中获取user，判断是否登录
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
        Visitor v = null;
        if (sessionUser != null) {
            //评论与用户关联
            comment.setCuser(userService.findUserById(sessionUser.getUserId()));
        } else {
            //如果用户为空，创建游客用户
            //获取客户端的IP地址
            String ipAddress = getRemortIP(request);
            if ("0:0:0:0:0:0:0:1".equals(ipAddress)) {
                ipAddress = "127.0.0.1";   //将本地IP地址0:0:0:0:0:0:0:1改为127.0.0.1
            }
            //去掉.
            ipAddress = ipAddress.replace(".", "");
            //根据ip判断是否存在游客，存在取出，不存在保存。
            v = visitorService.findVisitorByIP(ipAddress);
            if (v == null) {
                v = new Visitor(ipAddress);
                visitorService.saveVisitor(v);
            }
            //关联游客
            comment.setVisitor(v);

        }
        Blog blog = blogService.findBlogByID(blogId);
        comment.setCblog(blog);

        //保存评论
        commentService.saveComment(comment);

        //提取需要展示的评论
        CommentInfo commentInfo = new CommentInfo(comment.getCommentId(), null, null, null, null, null);
        if (sessionUser != null) {
            //关联用户展示的信息
            commentInfo.setUserId(sessionUser.getUserId());
            commentInfo.setUsername(sessionUser.getUsername());
            commentInfo.setHeadImgUrl(sessionUser.getHeadImgUrl());
        } else {
            //关联游客展示的信息
            commentInfo.setUserId(null);
            commentInfo.setUsername("游客" + v.getIpAddress());
            commentInfo.setHeadImgUrl(USER_HEAD_FIRST);
        }
        commentInfo.setContent(message);

        commentInfo.setCreateTime(comment.getCreateTime());
        return commentInfo;
    }

    /**
     * 删除博客评论
     */
    @PostMapping("/blog/comment/del")
    @ResponseBody
    public String delComment(@RequestParam(required = false) Integer commentId) {
        //根据id删除博客评论
        try {
            commentService.delCommentById(commentId);
        } catch (Exception e) {
            e.printStackTrace();
            return "delFail";
        }
        return "delSuccess";
    }

    /**
     * 点赞
     */
    @PostMapping("/blog/zan/update")
    @ResponseBody
    public String addZan(Integer zanNum, Integer blogId) {
        return blogService.saveZan(zanNum, blogId);
    }

    /**
     * 重新编辑博客
     */
    @GetMapping("/blog/edit")
    public String editBlog(Integer blogId, Model model) {

        Blog blog = blogService.findBlogByID(blogId);
        BlogEdit blogEdit = new BlogEdit(blog.getBlogId(), blog.getBlogTitle(),
                blog.getBlogText(), blog.getArtType(), blog.getBlogTag().getId(),
                blog.getCoverImgUrl());
        model.addAttribute("blog", blogEdit);

        return "blog/edit";
    }

    /**
     * 客户端的方法
     *
     * @param request
     * @return
     */
    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
