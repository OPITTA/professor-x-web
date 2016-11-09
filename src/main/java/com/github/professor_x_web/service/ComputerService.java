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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 510655387@qq.com
 */
@Service
public class ComputerService {

    private static final Logger logger = LoggerFactory.getLogger(ComputerService.class);
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

    public Cpu getCpuByComputerId(int computerId) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer != null) {
            int cpuId = computer.getCpuId();
            if (cpuId > 0) {
                return cpuMapper.selectByPrimaryKey(cpuId);
            }
        }
        return null;
    }

    public Mem getMemoryByComputerId(int computerId) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer != null) {
            int memId = computer.getMemId();
            if (memId > 0) {
                return memMapper.selectByPrimaryKey(memId);
            }
        }
        return null;
    }

    public Dist getDistByComputerId(int computerId) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer != null) {
            int distId = computer.getDistId();
            if (distId > 0) {
                return distMapper.selectByPrimaryKey(distId);
            }
        }
        return null;
    }

    public SolidDist getSolidDistByComputerId(int computerId) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer != null) {
            int solidDistId = computer.getSolidDistId();
            if (solidDistId > 0) {
                return solidDistMapper.selectByPrimaryKey(solidDistId);
            }
        }
        return null;
    }

    public NetworkCard getNerworkCardByComputerId(int computerId) {
        Computer computer = computerMapper.selectByPrimaryKey(computerId);
        if (computer != null) {
            int networkCardId = computer.getNetworkCardId();
            if (networkCardId > 0) {
                return networkCardMapper.selectByPrimaryKey(computerId);
            }
        }
        return null;
    }

    /**
     *
     * @param ids [1,2,3]
     * @return
     */
    public String getDescribesByIds(String ids) {
        String describes = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Integer> list = objectMapper.readValue(ids, ArrayList.class);
            if (list != null && !list.isEmpty()) {
                for (Integer id : list) {
                    Computer computer = computerMapper.selectByPrimaryKey(id);
                    describes = describes + computer.getDescribe() + ",";
                }
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        if (!describes.isEmpty()) {
            return describes.substring(0, describes.length() - 1);
        }
        return describes;
    }

}
