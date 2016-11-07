package com.br.professor_x_web.controller.manager;

import com.br.professor_x_web.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户注册信息时，进行提示信息(帐号唯一检测，邮箱注册信息检测)
 *
 * @author xin.cao@100credit.com
 */
@Controller
@RequestMapping(value = "/user/ajax/")
public class AjaxUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "check_account/{check_account}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> checkAccountAction(@PathVariable("check_account") String account) {
        Map<String, String> map = new HashMap<String, String>();
        if (!userService.haveAccount(account)) {
            map.put("is_ok", "1");
        } else {
            map.put("is_ok", "0");
        }
        return map;
    }

    @RequestMapping(value = "check_email/{check_email}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> checkEmailAction(@PathVariable("check_email") String email) {
        Map<String, String> map = new HashMap<String, String>();
        if (!userService.haveEmail(email)) {
            map.put("is_ok", "1");
        } else {
            map.put("is_ok", "0");
        }
        return map;
    }
}
