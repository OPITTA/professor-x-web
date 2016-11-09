/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.service;

import com.github.professor_x_web.mapper.ReportMapper;
import com.github.professor_x_web.model.ReportWithBLOBs;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 510655387@qq.com
 */
@Service
public class ReportService {

    @Autowired
    private ReportMapper reportMapper;

    public boolean createReport(int userId, ReportWithBLOBs report) {
        if (report == null) {
            return false;
        }
        if (report.getId() != null && report.getId() > 0) {
            ReportWithBLOBs realReport = reportMapper.selectByPrimaryKey(report.getId());
            if (realReport != null && realReport.getUserId() == userId) {
                reportMapper.updateByPrimaryKeySelective(report);
                return true;
            }
        }
        report.setId(null);
        report.setUserId(userId);
        return reportMapper.insertSelective(report) > 0;
    }

    public List<ReportWithBLOBs> getReportByUserId(int userId) {
        ReportWithBLOBs report = new ReportWithBLOBs();
        report.setUserId(userId);
        return reportMapper.selectAllSelective(report);
    }

    public ReportWithBLOBs getReportById(int id) {
        return reportMapper.selectByPrimaryKey(id);
    }
}
