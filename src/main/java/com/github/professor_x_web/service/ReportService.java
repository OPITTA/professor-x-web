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

    public boolean createReport(ReportWithBLOBs report) {
        return reportMapper.insertSelective(report) > 0;
    }

    public List<ReportWithBLOBs> getReportByUserId(int userId) {
        ReportWithBLOBs report = new ReportWithBLOBs();
        report.setUserId(userId);
        return reportMapper.selectAllSelective(report);
    }

}
