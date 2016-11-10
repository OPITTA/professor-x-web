package com.github.professor_x_web.controller.manager;

import com.github.professor_x_web.model.User;
import com.github.professor_x_web.constent.UserRole;
import com.github.professor_x_web.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
 * 用户管理
 *
 * @author 510655387@qq.com
 */
@Controller
@RequestMapping(value = "/manager/user_center/")
public class UserCenterController {

    private static final Logger logger = LoggerFactory.getLogger(UserCenterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listAction(HttpServletRequest httpServletRequest, Model model) {
        List<User> users = userService.selectAllSelective(null);
        model.addAttribute("users", users);
        return "/manager/user_center/list";
    }

    /**
     * 注册用户表单
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "register_form", method = RequestMethod.GET)
    public String registerFormAction(Model model) {
        model.addAttribute("User", new User());
        return "/manager/user_center/register_form";
    }

    /**
     * 注册用户
     *
     * @param httpServletRequest
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "do_register", method = RequestMethod.POST)
    public String doRegisterAction(HttpServletRequest httpServletRequest, @Valid User user, BindingResult bindingResult) {
        boolean ok;
        if (bindingResult.hasErrors()) {
            ok = false;
        } else {
            user.setUserRole(UserRole.COMMON.getId());
            ok = this.userService.createUser(user);
        }
        if (ok) {
            return "redirect:/manager/user_center/list";
        } else {
            return "redirect:/manager/user_center/register_form";
        }
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteAction(HttpServletRequest httpServletRequest) {
        String account = httpServletRequest.getParameter("account");
        if (account != null && !"".equals(account)) {
            userService.addBlacklist(account);
        }
        return "redirect:/manager/user_center/list";
    }

    @RequestMapping(value = "change_passwd_form", method = RequestMethod.GET)
    public String changePasswdFormAction(HttpServletRequest httpServletRequest, Model model) {
        List<User> users = userService.selectAllSelective(null);
        model.addAttribute("users", users);
        return "/manager/user_center/change_passwd_form";
    }

    @RequestMapping(value = "do_change_passwd", method = RequestMethod.POST)
    public String doChangePasswdAction(HttpServletRequest httpServletRequest) {
        String account = httpServletRequest.getParameter("account");
        String passwd = httpServletRequest.getParameter("passwd");
        if (account != null && !"".equals(account)) {
            userService.changePasswd(account, passwd);
        }
        return "redirect:/manager/user_center/change_passwd_form";
    }
}
