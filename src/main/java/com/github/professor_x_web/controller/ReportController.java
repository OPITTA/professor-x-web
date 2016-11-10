/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.controller;

import com.github.professor_x_web.constent.ReportStatus;
import com.github.professor_x_web.constent.Config;
import com.github.professor_x_web.model.Computer;
import com.github.professor_x_web.model.Data;
import com.github.professor_x_web.model.ReportWithBLOBs;
import com.github.professor_x_web.service.ComputerService;
import com.github.professor_x_web.service.ReportService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 510655387@qq.com
 */
@Controller
@RequestMapping("/report/")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

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
        model.addAttribute("NEW", ReportStatus.NEW);
        model.addAttribute("DOING", ReportStatus.DOING);
        model.addAttribute("DONE", ReportStatus.DONE);
        model.addAttribute("SHARED", ReportStatus.SHARED);
        model.addAttribute("reports", reports);
        return "/report/list";
    }

    @RequestMapping(value = "add/{report_id}", method = RequestMethod.GET)
    public String addAction(@PathVariable("report_id") Integer reportId, HttpServletRequest httpServletRequest, Model model) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        if (reportId != null && reportId > 0) {
            ReportWithBLOBs report = reportService.getReportById(reportId);
            if (report != null) {
                List<Computer> computers = computerService.getComputerByUserId(userId);
                model.addAttribute("computers", computers);
                model.addAttribute("report", report);
                model.addAttribute("NEW", ReportStatus.NEW);
                model.addAttribute("DOING", ReportStatus.DOING);
                model.addAttribute("DONE", ReportStatus.DONE);
                model.addAttribute("SHARED", ReportStatus.SHARED);
                String computerIds = report.getComputerIds();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    List<Integer> list = objectMapper.readValue(computerIds, ArrayList.class);
                    for (Computer c : computers) {
                        for (Integer computerId : list) {
                            if (c.getId() == computerId) {
                                c.setUsed(Boolean.TRUE);
                            }
                        }
                    }

                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
                return "/report/add";
            }
        }
        model.addAttribute("report", new ReportWithBLOBs());
        model.addAttribute("computers", computerService.getComputerByUserId(userId));
        model.addAttribute("NEW", ReportStatus.NEW);
        model.addAttribute("DOING", ReportStatus.DOING);
        model.addAttribute("DONE", ReportStatus.DONE);
        model.addAttribute("SHARED", ReportStatus.SHARED);
        return "/report/add";
    }

    @RequestMapping(value = "do_add", method = RequestMethod.POST)
    public String doAddAction(@Valid ReportWithBLOBs report, HttpServletRequest httpServletRequest, @RequestParam(value = "computerIds", required = false) List<String> computerIds, Model model, BindingResult bindingResult) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        if (computerIds == null || computerIds.isEmpty()) {
            return "redirect:/report/list";
        }
        StringBuilder formatComputerIds = new StringBuilder("[");
        for (String computerId : computerIds) {
            formatComputerIds.append(computerId).append(",");
        }
        if (formatComputerIds.length() > 1) {
            report.setComputerIds(formatComputerIds.substring(0, formatComputerIds.length() - 1) + "]");
        } else {
            report.setComputerIds("[]");
        }
        if (!bindingResult.hasErrors()) {
            reportService.createReport(userId, report);
        }
        return "redirect:/report/list";
    }

    @RequestMapping(value = "chart", method = RequestMethod.GET)
    public String chartAction(HttpServletRequest httpServletRequest, Model model, @RequestParam(value = "report_id", required = false) Integer reportId, @RequestParam(value = "start_time", required = false) String startTime, @RequestParam(value = "end_time", required = false) String endTime) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        List<ReportWithBLOBs> doingReports = reportService.getReportByUserIdAndStatus(userId, ReportStatus.DOING);
        List<ReportWithBLOBs> doneReports = reportService.getReportByUserIdAndStatus(userId, ReportStatus.DONE);
        List<ReportWithBLOBs> sharedReports = reportService.getReportByUserIdAndStatus(userId, ReportStatus.SHARED);
        List<ReportWithBLOBs> reports = new ArrayList<ReportWithBLOBs>();
        reports.addAll(doingReports);
        reports.addAll(doneReports);
        reports.addAll(sharedReports);
        model.addAttribute("reports", reports);
        if (reportId != null && reportId > 0) {
            List<Data> datas = reportService.getDatabyUserIdAndReportId(userId, reportId, startTime, endTime);
            model.addAttribute("datas", datas);
        }
        return "/report/chart";
    }
}
