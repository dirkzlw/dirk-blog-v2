package com.zlw.blog.controller;

import com.zlw.blog.po.Role;
import com.zlw.blog.po.User;
import com.zlw.blog.po.es.EsUser;
import com.zlw.blog.service.RoleService;
import com.zlw.blog.service.UserService;
import com.zlw.blog.service.es.EsUserService;
import com.zlw.blog.utils.FastDFSUtils;
import com.zlw.blog.utils.MD5Utils;
import com.zlw.blog.utils.UserUtils;
import com.zlw.blog.vo.ResultObj;
import com.zlw.blog.vo.SessionUser;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranger
 * @create 2019-06-04 19:41
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EsUserService esUserService;
    //注入javaMail发送器
    @Autowired
    private JavaMailSender mailSender;

    @Value("${USER_HEAD_FIRST}")
    private String USER_HEAD_FIRST;
    @Value("${USER_ROLE_FIRST}")
    private Integer USER_ROLE_FIRST;
    @Value("${FDFS_ADDRESS}")
    private String FDFS_ADDRESS;
    @Value("${FDFS_CLIENT_PAHT}")
    private String FDFS_CLIENT_PAHT;
    //邮件发送者
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${USER_PAGE_SIZE}")
    private Integer USER_PAGE_SIZE;
    @Value("${USER_INIT_PASSWORD}")
    private String USER_INIT_PASSWORD;
    @Value(("${USER_INIT_STATUS}"))
    private Integer USER_INIT_STATUS;
    @Value(("${USER_LOCK_STATUS}"))
    private Integer USER_LOCK_STATUS;

    /**
     * 跳转到登录页面
     */
    @GetMapping("/to/login")
    public String toLogin() {
        return "user/login";
    }

    /**
     * 跳转到用户修改信息
     */
    @GetMapping("/to/user/msg")
    public String toUserMsg() {
        return "user/msg";
    }

    /**
     * 跳转到用户管理界面
     *
     * @return
     */
    @GetMapping("/to/mgn/umgn")
    public String toUserMgn(Model model,
                            @RequestParam(required = false) Integer currentPage,
                            HttpServletRequest request) {
        if (currentPage == null) {
            currentPage = 0;
        }
        Page<EsUser> pageObj = esUserService.findEsUserByPage(currentPage, USER_PAGE_SIZE);
        List<EsUser> content = pageObj.getContent();
        com.zlw.blog.vo.Page userPage =
                new com.zlw.blog.vo.Page(content,
                        currentPage,
                        pageObj.getTotalPages(),
                        (int) pageObj.getTotalElements(),
                        content.size());

        model.addAttribute("userPage", userPage);
        return "mgn/umgn";
    }

    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping("/user/register")
    @ResponseBody
    public String register(User user, HttpServletRequest request, HttpServletResponse response) {

        //校验用户名邮箱是否已被注册
        String rtn = userService.checkUserNameAndEmail(user.getUsername(), user.getEmail());

        //用户名/邮箱重复
        if (!"success".equals(rtn)) {
            return rtn;
        }
        Role role = roleService.findRoleById(USER_ROLE_FIRST);
        //设置角色
        user.setRole(role);
        //设置头像
        user.setHeadImgUrl(USER_HEAD_FIRST);
        //密码加密
        user.setPassword(MD5Utils.md5(user.getPassword()));
        //设置状态
        user.setStatus(USER_INIT_STATUS);
        //保存
        userService.save(user);

        //同步es
        esUserService.saveEsUser(new EsUser(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().getRoleName(),
                user.getStatus()));

        UserUtils.saveObjectToSession(request,
                response,
                UserUtils.getSessionUser(user),
                "sessionUser");

        return rtn;
    }

    /**
     * 用户登录
     *
     * @return
     */
    @PostMapping("/user/login")
    @ResponseBody
    public String login(User logUser, HttpServletRequest request, HttpServletResponse response) {
        //校验
        User user = userService.login(logUser.getUsername(), MD5Utils.md5(logUser.getPassword()));
        if (user != null) {
            if(user.getStatus()==USER_LOCK_STATUS){
                return "lock";
            }
            UserUtils.saveObjectToSession(request,
                    response,
                    UserUtils.getSessionUser(user),
                    "sessionUser");
            return "success";
        }

        return "fail";
    }

    /**
     * 重置密码
     */
    @PostMapping("/user/pwd/reset")
    @ResponseBody
    public String pwdReset(String email) {

        User user = userService.findUserByEmail(email);
        if (user == null) {
            return "noneEmail";
        }

        //给邮箱发送重置的密码
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(fromEmail);

        //设置邮件的信息
        String newpwd = (int) ((Math.random() * 9 + 1) * 100000) + "";    //随机生成密码
        message.setTo(email);
        message.setSubject("来自DirkBlog的消息");
        message.setText("您的新密码为:" + newpwd + ".\n请登录后及时修改密码.\n");
        //发送
        mailSender.send(message);

        //保存用户新密码
        user.setPassword(MD5Utils.md5(newpwd));

        userService.save(user);

        return "resetSuccess";
    }

    /**
     * 修改用户名
     *
     * @param userId      用户id
     * @param newUsername 新用户名
     * @param request     请求
     * @param response    响应
     * @return 修改结果
     */
    @PostMapping("/user/username/reset")
    @ResponseBody
    public String usernameReset(Integer userId,
                                String newUsername,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        String result = userService.userNameReset(userId, newUsername);
        if ("success".equals(result)) {
            //修改用户名后 同步es库
            EsUser esUser = esUserService.findEsUserById(userId);
            esUser.setUsername(newUsername);
            esUserService.saveEsUser(esUser);

            SessionUser sessionUser = (SessionUser) UserUtils.getObjectFromSession(request, "sessionUser");
            sessionUser.setUsername(newUsername);
            UserUtils.saveObjectToSession(request, response, sessionUser, "sessionUser");
            return "success";
        } else {
            return "userNameExist";
        }
    }

    /**
     * 修改密码
     *
     * @param userId     用户名
     * @param oldUserpwd 旧密码
     * @param newUserpwd 新密码
     * @return 修改结果
     */
    @PostMapping("/user/userpwd/reset")
    @ResponseBody
    public String userpwdReset(Integer userId, String oldUserpwd, String newUserpwd) {
        String result = userService.userPwdReset(userId, oldUserpwd, newUserpwd);
        if ("success".equals(result)) {
            return "success";
        } else {
            return "oldUserpwdFalse";
        }
    }

    /**
     * 修改邮箱
     *
     * @param userId   用户ID
     * @param newEmail 用户修改后邮箱
     * @return 修改结果
     */
    @PostMapping("/user/email/reset")
    @ResponseBody
    public String userEamilReset(Integer userId,
                                 String newEmail,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        String result = userService.userEmailReset(userId, newEmail);

        if ("success".equals(result)) {
            //更新es
            EsUser esUser = esUserService.findEsUserById(userId);
            esUser.setEmail(newEmail);
            esUserService.saveEsUser(esUser);

            //更新sessionUser
            SessionUser sessionUser = (SessionUser) UserUtils.getObjectFromSession(request, "sessionUser");
            sessionUser.setEmail(newEmail);
            UserUtils.saveObjectToSession(request, response, sessionUser, "sessionUser");
        }
        return result;
    }

    /**
     * 修改头像
     *
     * @param userId     用户ID
     * @param newHeadUrl 用户新头像
     * @param request    请求
     * @param response   响应
     * @return 修改结果
     */
    @PostMapping("/user/headurl/reset")
    @ResponseBody
    public String HeadUrlReset(Integer userId,
                               MultipartFile newHeadUrl,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        //上传文件至服务器
        String newUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT,
                FDFS_ADDRESS,
                newHeadUrl);
        String result = "";
        if (newUrl != null || !"".equals(newUrl)) {
            result = userService.HeadUrlReset(userId, newUrl);
        }
        if ("success".equals(result)) {
            SessionUser sessionUser = (SessionUser) UserUtils.getObjectFromSession(request, "sessionUser");
            sessionUser.setHeadImgUrl(newUrl);
            UserUtils.saveObjectToSession(request, response, sessionUser, "sessionUser");
            return "success";
        }
        return "fail";
    }

    /**
     * 管理员添加用户
     * @param user
     * @param roleName
     * @return
     */
    @PostMapping("/mgn/umgn/save")
    @ResponseBody
    public ResultObj saveUser(User user, String roleName) {
        String result;
        System.out.println("roleName = " + roleName);
        Role role = roleService.findByRoleName(roleName);
        EsUser esUser = new EsUser();
        if (user.getUserId() == null) {
            // 添加
            user.setRole(role);
            user.setPassword(MD5Utils.md5(USER_INIT_PASSWORD));
            user.setHeadImgUrl(USER_HEAD_FIRST);
            user.setStatus(USER_INIT_STATUS);
            result = userService.checkAndSave(user);
        } else {
            //修改
            user.setRole(role);
            User oldUser = userService.findUserById(user.getUserId());
            oldUser.setUsername(user.getUsername());
            oldUser.setEmail(user.getEmail());
            oldUser.setRole(user.getRole());
            user.setStatus(oldUser.getStatus());
            result = userService.saveEditedUser(oldUser);
        }
        ResultObj rtnObj = new ResultObj();
        //同步搜索库
        if ("save".equals(result)) {
            esUser.setUserId(user.getUserId());
            esUser.setUsername(user.getUsername());
            esUser.setEmail(user.getEmail());
            esUser.setRoleName(user.getRole().getRoleName());
            esUser.setStatus(user.getStatus());
            esUserService.saveEsUser(esUser);
        }
        rtnObj.setObj(esUser);
        rtnObj.setRtn(result);
        System.out.println("UserController.saveUser");
        return rtnObj;
    }

    /**
     * 将用户加入黑名单
     * @param userId
     * @return
     */
    @PostMapping("/mgn/umgn/del")
    @ResponseBody
    public String delUser(Integer userId){

        try {
            lockAndReuse(userService,
                    esUserService,
                    userId,
                    USER_LOCK_STATUS);
        }catch (Exception e){
            return "fail";
        }

        return "success";
    }

    /**
     * 启用用户
     * @param userId
     * @return
     */
    @PostMapping("/mgn/umgn/reuse")
    @ResponseBody
    public String reuseUser(Integer userId){

        try {
           lockAndReuse(userService,
                   esUserService,
                   userId,
                   USER_INIT_STATUS);
        }catch (Exception e){
            return "fail";
        }

        return "success";
    }

    /**
     * 重置用户密码
     * @return
     */
    @PostMapping("/mgn/umgn/rpd")
    @ResponseBody
    public String resetPwd(Integer userId){
        try {
            User user = userService.findUserById(userId);
            user.setPassword(MD5Utils.md5(USER_INIT_PASSWORD));
            userService.save(user);
        }catch (Exception e){
            return "fail";
        }

        return "success";
    }

    /**
     * 用户退出
     *
     * @return
     */
    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //清除cookie和session
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
        }

        request.getSession().removeAttribute("sessionUser");

        return "redirect:/index";
    }

    private static void lockAndReuse(UserService userService,
                                     EsUserService esUserService,
                                     Integer userId,
                                     Integer status){
        User user = userService.findUserById(userId);
        user.setStatus(status);
        userService.save(user);
        //同步es
        EsUser esUser = esUserService.findEsUserById(userId);
        esUser.setStatus(status);
        esUserService.saveEsUser(esUser);
    }
}
