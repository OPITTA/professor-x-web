package com.github.professor_x_web.mapper;

import com.github.professor_x_web.model.SolidDist;
import java.util.List;

public interface SolidDistMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(SolidDist record);

    int insertSelective(SolidDist record);

    SolidDist selectByPrimaryKey(Integer id);

    SolidDist selectSelective(SolidDist record);

    List<SolidDist> selectAllSelective(SolidDist record);

    int updateByPrimaryKeySelective(SolidDist record);

    int updateByPrimaryKey(SolidDist record);
}
