package com.github.professor_x_web.mapper;

import com.github.professor_x_web.model.Dist;
import java.util.List;

public interface DistMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Dist record);

    int insertSelective(Dist record);

    Dist selectByPrimaryKey(Integer id);

    Dist selectSelective(Dist record);

    List<Dist> selectAllSelective(Dist record);

    int updateByPrimaryKeySelective(Dist record);

    int updateByPrimaryKey(Dist record);
}
