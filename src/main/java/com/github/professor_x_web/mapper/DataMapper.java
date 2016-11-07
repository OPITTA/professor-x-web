package com.github.professor_x_web.mapper;

import com.github.professor_x_web.model.Data;
import java.util.List;

public interface DataMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Data record);

    int insertSelective(Data record);

    Data selectByPrimaryKey(Integer id);

    Data selectSelective(Data record);

    List<Data> selectAllSelective(Data record);

    int updateByPrimaryKeySelective(Data record);

    int updateByPrimaryKey(Data record);
}
