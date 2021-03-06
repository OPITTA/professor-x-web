/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.service;

import com.github.professor_x_web.constent.ReportStatus;
import com.github.professor_x_web.constent.ResultStatus;
import com.github.professor_x_web.mapper.DataMapper;
import com.github.professor_x_web.mapper.ReportMapper;
import com.github.professor_x_web.model.Data;
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
    @Autowired
    private DataMapper dataMapper;

    public boolean createReport(int userId, ReportWithBLOBs report) {
        if (report == null) {
            return false;
        }
        if (report.getTitle() == null || report.getTitle().isEmpty()) {
            return false;
        }
        ReportWithBLOBs realReport = new ReportWithBLOBs();
        realReport.setUserId(userId);
        realReport.setTitle(report.getTitle());
        realReport = reportMapper.selectByPrimaryKey(report.getId());
        if (realReport != null) {
            reportMapper.updateByPrimaryKeySelective(report);
            return true;
        }
        report.setId(null);
        report.setUserId(userId);
        report.setStatus(ReportStatus.NEW.getId());
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

    public List<ReportWithBLOBs> getReportByUserIdAndStatus(int userId, ReportStatus reportStatus) {
        ReportWithBLOBs report = new ReportWithBLOBs();
        report.setUserId(userId);
        report.setStatus(reportStatus.getId());
        return reportMapper.selectAllSelective(report);
    }

    public List<Data> getDatabyUserIdAndReportId(int userId, int reportId, String startTime, String endTime) {
        ReportWithBLOBs report = reportMapper.selectByPrimaryKey(reportId);
        if (report == null) {
            return null;
        }
        if (report.getUserId() != userId) {
            return null;
        }
        Data data = new Data();
        data.setReportId(reportId);
        if (startTime != null && !startTime.isEmpty()) {
            data.setStartTime(startTime + " 00:00:00");
        }
        if (endTime != null && !endTime.isEmpty()) {
            data.setEndTime(endTime + " 23:59:59");
        }
        return dataMapper.selectAllSelective(data);
    }

    public ResultStatus addDataForReport(Integer userId, String title, Data data) {
        ReportWithBLOBs report = new ReportWithBLOBs();
        report.setUserId(userId);
        report.setTitle(title);
        report = reportMapper.selectSelective(report);
        if (report == null) {
            return ResultStatus.NO;
        }
        if (report.getStatus() == ReportStatus.NEW.getId()) {
            report.setStatus(ReportStatus.DOING.getId());
        }
        data.setReportId(report.getId());
        if (dataMapper.insertSelective(data) > 0) {
            reportMapper.updateByPrimaryKeySelective(report);
            return ResultStatus.YES;
        }
        return ResultStatus.NO;
    }
}
