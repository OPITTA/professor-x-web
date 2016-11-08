package com.github.professor_x_web.mapper;

import com.github.professor_x_web.model.NetworkCard;
import java.util.List;

public interface NetworkCardMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(NetworkCard record);

    int insertSelective(NetworkCard record);

    NetworkCard selectByPrimaryKey(Integer id);

    NetworkCard selectSelective(NetworkCard record);

    List<NetworkCard> selectAllSelective(NetworkCard record);

    int updateByPrimaryKeySelective(NetworkCard record);

    int updateByPrimaryKey(NetworkCard record);
}
