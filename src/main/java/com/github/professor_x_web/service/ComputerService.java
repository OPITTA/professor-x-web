/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.service;

import com.github.professor_x_web.mapper.ComputerMapper;
import com.github.professor_x_web.mapper.CpuMapper;
import com.github.professor_x_web.mapper.DistMapper;
import com.github.professor_x_web.mapper.MemMapper;
import com.github.professor_x_web.mapper.SolidDistMapper;
import com.github.professor_x_web.model.Computer;
import com.github.professor_x_web.model.Cpu;
import com.github.professor_x_web.model.Dist;
import com.github.professor_x_web.model.Mem;
import com.github.professor_x_web.model.SolidDist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 510655387@qq.com
 */
@Service
public class ComputerService {

    @Autowired
    private ComputerMapper computerMapper;
    @Autowired
    private CpuMapper cpuMapper;
    @Autowired
    private MemMapper memMapper;
    @Autowired
    private DistMapper distMapper;
    @Autowired
    private SolidDistMapper solidDistMapper;

    /**
     * 创建cpu, 存在修改, 不存在添加
     *
     * @param computerId
     * @param cpu
     * @return
     */
    public boolean createCpu(int computerId, Cpu cpu) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer == null) {
            return false;
        }
        if (computer.getCpuId() > 0) {
            cpuMapper.updateByPrimaryKeySelective(cpu);
        } else {
            int cpuId = cpuMapper.insertSelective(cpu);
            computer.setCpuId(cpuId);
            computerMapper.updateByPrimaryKeySelective(computer);
        }
        return true;
    }

    /**
     *
     * @param computerId
     * @param mem
     * @return
     */
    public boolean createMem(int computerId, Mem mem) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer == null) {
            return false;
        }
        if (computer.getCpuId() > 0) {
            memMapper.updateByPrimaryKeySelective(mem);
        } else {
            int memId = memMapper.insertSelective(mem);
            computer.setCpuId(memId);
            computerMapper.updateByPrimaryKeySelective(computer);
        }
        return true;
    }

    /**
     *
     * @param computerId
     * @param dist
     * @return
     */
    public boolean createDist(int computerId, Dist dist) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer == null) {
            return false;
        }
        if (computer.getCpuId() > 0) {
            distMapper.updateByPrimaryKeySelective(dist);
        } else {
            int distId = distMapper.insertSelective(dist);
            computer.setCpuId(distId);
            computerMapper.updateByPrimaryKeySelective(computer);
        }
        return true;
    }

    /**
     *
     * @param computerId
     * @param solidDist
     * @return
     */
    public boolean createSolidDist(int computerId, SolidDist solidDist) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer == null) {
            return false;
        }
        if (computer.getCpuId() > 0) {
            solidDistMapper.updateByPrimaryKeySelective(solidDist);
        } else {
            int solidDistId = solidDistMapper.insertSelective(solidDist);
            computer.setCpuId(solidDistId);
            computerMapper.updateByPrimaryKeySelective(computer);
        }
        return true;
    }

    public boolean createComputer(Computer computer) {
        return computerMapper.insertSelective(computer) > 0;
    }
}
