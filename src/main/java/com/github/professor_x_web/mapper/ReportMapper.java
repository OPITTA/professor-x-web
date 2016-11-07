package com.github.professor_x_web.mapper;

import com.github.professor_x_web.model.Report;
import com.github.professor_x_web.model.ReportWithBLOBs;
import java.util.List;

public interface ReportMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ReportWithBLOBs record);

    int insertSelective(ReportWithBLOBs record);

    ReportWithBLOBs selectByPrimaryKey(Integer id);

    ReportWithBLOBs selectSelective(ReportWithBLOBs record);

    List<ReportWithBLOBs> selectAllSelective(ReportWithBLOBs record);

    int updateByPrimaryKeySelective(ReportWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ReportWithBLOBs record);

    int updateByPrimaryKey(Report record);
}
