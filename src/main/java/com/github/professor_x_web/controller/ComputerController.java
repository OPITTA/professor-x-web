/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.controller;

import com.github.professor_x_web.constent.Config;
import com.github.professor_x_web.model.Computer;
import com.github.professor_x_web.service.ComputerService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 510655387@qq.com
 */
@Controller
@RequestMapping("/computer/")
public class ComputerController {

    private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

    @Autowired
    private ComputerService computerService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listAction(HttpServletRequest httpServletRequest, Model model) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        logger.info("userId = {}", userId);
        List<Computer> computers = computerService.getComputerByUserId(userId);
        model.addAttribute("computers", computers);
        return "/computer/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addAction(HttpServletRequest httpServletRequest, Model model) {
        return "/computer/add";
    }

    @RequestMapping(value = "do_add", method = RequestMethod.POST)
    public String doAddAction(HttpServletRequest httpServletRequest, Model model) {
        String describe = httpServletRequest.getParameter("describe");
        if (describe != null && !describe.isEmpty()) {
            HttpSession httpSession = httpServletRequest.getSession();
            Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
            Computer computer = new Computer();
            computer.setUserId(userId);
            computer.setDescribe(describe);
            computerService.createComputer(computer);
        }
        return "redirect:/computer/list";
    }
}
