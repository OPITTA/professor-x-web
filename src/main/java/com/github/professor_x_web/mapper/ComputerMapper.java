package com.github.professor_x_web.mapper;

import com.github.professor_x_web.model.Computer;
import java.util.List;

public interface ComputerMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Computer record);

    int insertSelective(Computer record);

    Computer selectByPrimaryKey(Integer id);

    Computer selectSelective(Computer record);

    List<Computer> selectAllSelective(Computer record);

    int updateByPrimaryKeySelective(Computer record);

    int updateByPrimaryKey(Computer record);
}
