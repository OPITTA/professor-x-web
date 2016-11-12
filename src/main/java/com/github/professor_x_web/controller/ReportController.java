/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.controller;

import com.github.professor_x_web.constent.ReportStatus;
import com.github.professor_x_web.constent.Config;
import com.github.professor_x_web.constent.ResultStatus;
import com.github.professor_x_web.model.Computer;
import com.github.professor_x_web.model.Data;
import com.github.professor_x_web.model.ReportWithBLOBs;
import com.github.professor_x_web.service.ComputerService;
import com.github.professor_x_web.service.ReportService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.comparator.ComparableComparator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            model.addAttribute("reportId", reportId);
            List<Data> datas = reportService.getDatabyUserIdAndReportId(userId, reportId, startTime, endTime);
            if (datas != null && !datas.isEmpty()) {
                Collections.sort(datas, new Comparator<Data>() {

                    public int compare(Data o1, Data o2) {
                        return o1.getConcurrency() - o2.getConcurrency();
                    }
                });
                List<Integer> concurrencys = new ArrayList<Integer>();
                List<Double> tpses = new ArrayList<Double>();
                List<Double> averageRts = new ArrayList<Double>();
                List<Integer> minRts = new ArrayList<Integer>();
                List<Integer> maxRts = new ArrayList<Integer>();
                List<Double> errorRates = new ArrayList<Double>();
                for (Data data : datas) {
                    concurrencys.add(data.getConcurrency());
                    tpses.add(data.getTps());
                    averageRts.add(data.getAverageRt());
                    minRts.add(data.getMinRt());
                    maxRts.add(data.getMaxRt());
                    errorRates.add(data.getErrorRate());
                }
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    model.addAttribute("concurrencys", objectMapper.writeValueAsString(concurrencys));
                    model.addAttribute("tpses", objectMapper.writeValueAsString(tpses));
                    model.addAttribute("averageRts", objectMapper.writeValueAsString(averageRts));
                    model.addAttribute("minRts", objectMapper.writeValueAsString(minRts));
                    model.addAttribute("maxRts", objectMapper.writeValueAsString(maxRts));
                    model.addAttribute("errorRates", objectMapper.writeValueAsString(errorRates));
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
            model.addAttribute("datas", datas);
        }
        return "/report/chart";
    }

    @RequestMapping(value = "do_add_data", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, String> doAddDataAction(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        Map<String, String> result = new HashMap<String, String>();
        StringBuilder sb = new StringBuilder();
        try {
            ServletInputStream sis = httpServletRequest.getInputStream();
            GZIPInputStream gzipis = new GZIPInputStream(sis);
            InputStreamReader isr = new InputStreamReader(gzipis);
            BufferedReader br = new BufferedReader(isr);
            String buf;
            while ((buf = br.readLine()) != null) {
                sb.append(buf);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            result.put("result", ResultStatus.NO.getName());
            return result;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Data data;
        try {
            data = objectMapper.readValue(sb.toString(), Data.class);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
            result.put("result", ResultStatus.NO.getName());
            return result;
        }
        String topic = data.getTopic();
        String title = data.getTitle();
        if (userId == null || userId < 1) {
            result.put("result", ResultStatus.NO.getName());
            return result;
        }
        if (topic == null || topic.isEmpty()) {
            result.put("result", ResultStatus.NO.getName());
            return result;
        }
        if (title == null || title.isEmpty()) {
            result.put("result", ResultStatus.NO.getName());
            return result;
        }
        logger.info("topic = {}, title = {}, data = {}", sb.toString());
        ResultStatus rs = reportService.addDataForReport(userId, topic, data);
        result.put("result", rs.getName());
        return result;
    }
}
