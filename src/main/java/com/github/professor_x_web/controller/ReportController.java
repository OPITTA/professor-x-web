/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.controller;

import com.github.professor_x_web.constent.Config;
import com.github.professor_x_web.model.ReportWithBLOBs;
import com.github.professor_x_web.service.ComputerService;
import com.github.professor_x_web.service.ReportService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 510655387@qq.com
 */
@Controller
@RequestMapping("/report/")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private ComputerService computerService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listAction(HttpServletRequest httpServletRequest, Model model) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        List<ReportWithBLOBs> reports = reportService.getReportByUserId(userId);
        for (ReportWithBLOBs report : reports) {
            report.setComputerIds(computerService.getDescribesByIds(report.getComputerIds()));
        }
        model.addAttribute("reports", reports);
        return "/report/list";
    }

    @RequestMapping(value = "add/{report_id}", method = RequestMethod.GET)
    public String addAction(@PathVariable("report_id") Integer reportId, HttpServletRequest httpServletRequest, Model model) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        if (reportId != null && reportId > 0) {
            ReportWithBLOBs report = reportService.getReportById(reportId);
            if(report != null) {
                model.addAttribute("report", report);
                return "/report/add";
            }
        }
        model.addAttribute("report", new ReportWithBLOBs());
        model.addAttribute("computers", computerService.getComputerByUserId(userId));
        return "/report/add";
    }

    @RequestMapping(value = "do_add", method = RequestMethod.POST)
    public String doAddAction(@Valid ReportWithBLOBs report, HttpServletRequest httpServletRequest, Model model, BindingResult bindingResult) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        if (!bindingResult.hasErrors()) {
            reportService.createReport(userId, report);
        }
        return "redirect:/report/list";
    }

}
