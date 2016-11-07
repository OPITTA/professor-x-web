package com.github.professor_x_web.mapper;

import com.github.professor_x_web.model.Mem;
import java.util.List;

public interface MemMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Mem record);

    int insertSelective(Mem record);

    Mem selectByPrimaryKey(Integer id);

    Mem selectSelective(Mem record);

    List<Mem> selectAllSelective(Mem record);

    int updateByPrimaryKeySelective(Mem record);

    int updateByPrimaryKey(Mem record);
}
