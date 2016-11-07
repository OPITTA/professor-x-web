/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.service;

import com.github.professor_x_web.mapper.DataMapper;
import com.github.professor_x_web.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 510655387@qq.com
 */
@Service
public class DataService {

    @Autowired
    private DataMapper dataMapper;

    public boolean createData(Data data) {
        return dataMapper.insertSelective(data) > 0;
    }
}
