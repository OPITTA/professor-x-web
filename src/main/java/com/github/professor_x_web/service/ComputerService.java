/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.service;

import com.github.professor_x_web.mapper.ComputerMapper;
import com.github.professor_x_web.mapper.CpuMapper;
import com.github.professor_x_web.mapper.DistMapper;
import com.github.professor_x_web.mapper.MemMapper;
import com.github.professor_x_web.mapper.NetworkCardMapper;
import com.github.professor_x_web.mapper.SolidDistMapper;
import com.github.professor_x_web.model.Computer;
import com.github.professor_x_web.model.Cpu;
import com.github.professor_x_web.model.Dist;
import com.github.professor_x_web.model.Mem;
import com.github.professor_x_web.model.NetworkCard;
import com.github.professor_x_web.model.SolidDist;
import java.util.List;
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
    @Autowired
    private NetworkCardMapper networkCardMapper;

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
            cpuMapper.insertSelective(cpu);
            computer.setCpuId(cpu.getId());
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
        if (computer.getMemId() > 0) {
            memMapper.updateByPrimaryKeySelective(mem);
        } else {
            memMapper.insertSelective(mem);
            computer.setMemId(mem.getId());
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
        if (computer.getDistId() > 0) {
            distMapper.updateByPrimaryKeySelective(dist);
        } else {
            distMapper.insertSelective(dist);
            computer.setDistId(dist.getId());
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
        if (computer.getSolidDistId() > 0) {
            solidDistMapper.updateByPrimaryKeySelective(solidDist);
        } else {
            solidDistMapper.insertSelective(solidDist);
            computer.setSolidDistId(solidDist.getId());
            computerMapper.updateByPrimaryKeySelective(computer);
        }
        return true;
    }

    /**
     *
     * @param computerId
     * @param networkCard
     * @return
     */
    public boolean createNetworkCard(int computerId, NetworkCard networkCard) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer == null) {
            return false;
        }
        if (computer.getNetworkCardId() > 0) {
            networkCardMapper.updateByPrimaryKeySelective(networkCard);
        } else {
            networkCardMapper.insertSelective(networkCard);
            computer.setNetworkCardId(networkCard.getId());
            computerMapper.updateByPrimaryKeySelective(computer);
        }
        return true;
    }

    /**
     *
     * @param computer
     * @return
     */
    public boolean createComputer(Computer computer) {
        return computerMapper.insertSelective(computer) > 0;
    }

    /**
     *
     * @param userId
     * @return
     */
    public List<Computer> getComputerByUserId(int userId) {
        Computer computer = new Computer();
        computer.setUserId(userId);
        return computerMapper.selectAllSelective(computer);
    }

}
