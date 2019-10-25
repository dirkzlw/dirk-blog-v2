package com.zlw.blog.controller;

import com.zlw.blog.po.Role;
import com.zlw.blog.po.User;
import com.zlw.blog.service.RoleService;
import com.zlw.blog.service.UserService;
import com.zlw.blog.utils.FastDFSUtils;
import com.zlw.blog.utils.MD5Utils;
import com.zlw.blog.utils.UserUtils;
import com.zlw.blog.vo.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
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
    //注入javaMail发送器
    @Autowired
    private JavaMailSender mailSender;

    @Value("${USER_HEAD_FIRST}")
    private String USER_HEAD_FIRST;
    @Value("${USER_ROLE_FIRST}")
    private Integer USER_ROLE_FIRST;
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FDFS_CLIENT_PAHT}")
    private String FDFS_CLIENT_PAHT;
    //邮件发送者
    @Value("${spring.mail.username}")
    private String fromEmail;

    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping("/user/register")
    @ResponseBody
    public String register(User user, HttpServletRequest request, HttpServletResponse response) {

        //校验用户名邮箱是否已被注册
        String rtn = userService.checkUserNameAndEmail(user.getUsername(),user.getEmail());

        //用户名/邮箱重复
        if(!"success".equals(rtn)){
            return rtn;
        }
        Role role = roleService.findRoleById(USER_ROLE_FIRST);
        //设置角色
        user.setRole(role);
        //设置头像
        user.setHeadImgUrl(USER_HEAD_FIRST);
        //密码加密
        user.setPassword(MD5Utils.md5(user.getPassword()));
        //保存
        userService.save(user);

        //将JSESSIONID存于本地cookie中
        Cookie cookiesessionid = new Cookie("JSESSIONID", request.getSession().getId());
        cookiesessionid.setMaxAge(24 * 60 * 60);
        response.addCookie(cookiesessionid);

        //存Session
        //根据用户角色，设置用户权限
        HttpSession session = request.getSession();
        session.setAttribute("sessionUser",
                UserUtils.getSessionUser(user));
        session.setMaxInactiveInterval(3 * 24 * 60);    //设置session生存时间

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

            //将JSESSIONID存于本地cookie中
            Cookie cookiesessionid = new Cookie("JSESSIONID", request.getSession().getId());
            cookiesessionid.setMaxAge(24 * 60 * 60);
            response.addCookie(cookiesessionid);

            //存Session
            //根据用户角色，设置用户权限
            HttpSession session = request.getSession();
            //将User转为SessionUser
            session.setAttribute("sessionUser", UserUtils.getSessionUser(user));
            session.setMaxInactiveInterval(3 * 24 * 60);    //设置session生存时间

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

        //数据库中邮箱不存在，则给注册的邮件发送验证码
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

        return "redirect:/";
    }

    /**
     * 用户修改头像
     *
     * @param userId
     * @param headImg
     * @throws IOException
     */
    @RequestMapping("/user/manage/changeHeadImg")
    @ResponseBody
    public String changeHeadImg(@RequestParam Integer userId,
                                @RequestParam MultipartFile headImg,
                                HttpServletRequest request) {

        //根据id查询数据库中用户
        User user = userService.findUserById(userId);

        try {
            //上传文件
            String imgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FTP_ADDRESS, headImg);
            user.setHeadImgUrl(imgUrl);
            userService.save(user);

        } catch (Exception e) {
            e.printStackTrace();
            return "HeadImgError";
        }

        //更新session
        updateSession(request,UserUtils.getSessionUser(user));

        return "HeadImgOk";
    }

    /**
     * 用户修改用户名/邮箱
     *
     * @param userId
     * @param username
     * @param email
     * @throws IOException
     */
    @PostMapping("/user/manage/magChange")
    @ResponseBody
    public String checkMsgChange(@RequestParam Integer userId,
                                 @RequestParam String username,
                                 @RequestParam String email,
                                 HttpServletRequest request) {

//        //根据id查询数据库中用户
//        User oldUser = userService.findUserById(userId);
//
//        //校验修正的信息
//        if (!oldUser.getUsername().equals(username) && !oldUser.getEmail().equals(email)) {
//            //修改用户名和邮箱
//            boolean b = userService.checkEmailExist(email);
//            if (b) {
//                return "EmailUsed";
//            } else {
//                oldUser.setEmail(email);
//                oldUser.setUsername(username);
//                userService.save(oldUser);
//                //更新session
//                updateSession(request, oldUser);
//                return "EmailUsernameOk";
//            }
//        } else if (!oldUser.getUsername().equals(username)) {
//            //只修改了用户名
//            oldUser.setUsername(username);
//            userService.save(oldUser);
//            //更新session
//            updateSession(request, oldUser);
//            return "UsernameOk";
//        } else if (!oldUser.getEmail().equals(email)) {
//            //只修改了邮箱
//            boolean b = userService.checkEmailExist(email);
//            if (b) {
//                return "EmailUsed";
//            } else {
//                oldUser.setEmail(email);
//                userService.save(oldUser);
//                //更新session
//                updateSession(request, oldUser);
//                return "EmailOk";
//            }
//        } else {
//            return "NothingDo";
//        }
        return null;
    }

    /**
     * 修改密码
     *
     * @param userId
     * @param oldpwd
     * @param newpwd
     * @throws IOException
     */
    @PostMapping("/user/manage/pwdChange")
    @ResponseBody
    public String checkPwdChange(@RequestParam Integer userId,
                                 @RequestParam String oldpwd,
                                 @RequestParam String newpwd,
                                 HttpServletRequest request) throws IOException {

        //根据id查询数据库中用户
        User oldUser = userService.findUserById(userId);

        //对原密码加密
        oldpwd = MD5Utils.md5(oldpwd);

        //校验修正的信息
        if (!oldpwd.equals(oldUser.getPassword())) {
            //与原密码不符
            return "PwdError";
        } else {
            //原密码输入正确
            //对新密码进行加密
            newpwd = MD5Utils.md5(newpwd);
            //更新密码
            oldUser.setPassword(newpwd);
            userService.save(oldUser);
            //修改成功
            //更新session
            updateSession(request, UserUtils.getSessionUser(oldUser));
            return "PwdOk";
        }
    }

    /**
     * 更新session，多处使用抽象出来
     *
     * @param request
     * @param sessionUser
     */
    private void updateSession(HttpServletRequest request, SessionUser sessionUser) {
        //更新session
        //根据用户角色，设置用户权限
        HttpSession session = request.getSession();
        session.setAttribute("sessionUser", sessionUser);
        session.setMaxInactiveInterval(3 * 24 * 60);    //设置session生存时间
    }

}
