package com.github.professor_x_web.mapper;

import com.github.professor_x_web.model.Cpu;
import java.util.List;

public interface CpuMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Cpu record);

    int insertSelective(Cpu record);

    Cpu selectByPrimaryKey(Integer id);

    Cpu selectSelective(Cpu record);

    List<Cpu> selectAllSelective(Cpu record);

    int updateByPrimaryKeySelective(Cpu record);

    int updateByPrimaryKey(Cpu record);
}
