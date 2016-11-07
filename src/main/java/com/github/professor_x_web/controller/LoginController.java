package com.github.professor_x_web.controller;

import com.github.professor_x_web.constent.Config;
import com.github.professor_x_web.model.User;
import com.github.professor_x_web.constent.UserRole;
import com.github.professor_x_web.service.UserService;
import com.github.professor_x_web.util.MdImplement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author xin.cao@100credit.com
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String rootAction() {
        return "redirect:/login_form";
    }

    /**
     * 登陆用户表单
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "login_form", method = RequestMethod.GET)
    public String loginFormUserAction(Model model) {
        model.addAttribute("User", new User());
        return "/login_form";
    }

    /**
     * 用户登陆 登录包括两部分内容，1.身份识别，2权限限制（缺少）
     *
     * @param httpServletRequest
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "do_login", method = RequestMethod.POST)
    public String doLoginAction(HttpServletRequest httpServletRequest, @Valid User user, BindingResult bindingResult) {
        boolean ok;
        if (bindingResult.hasErrors()) {
            return "redirect:/login_form";
        }
        String inputPass = MdImplement.encodeMD5To32(user.getPasswd().toLowerCase());
        user.setPasswd(inputPass);
        ok = this.userService.loginCheck(user);
        if (ok) {
            HttpSession httpSession = httpServletRequest.getSession(true);
            UserRole userRole = UserRole.fromId(user.getUserRole());
            httpSession.setAttribute(Config.USER_ROLE, userRole.getKey());
            if (userRole != UserRole.SUPER_ADMIN) {
                httpSession.setAttribute(Config.PLATFORM_ID, user.getPlatformId());
            } else {
                httpSession.setAttribute(Config.PLATFORM_ID, -1);
            }
            // 登录成功, 跳转的界面
            return "redirect:/manager/user_center/list";
        }
        logger.error("login error account={}", user.getAccount());
        return "redirect:/login_form";
    }
}
