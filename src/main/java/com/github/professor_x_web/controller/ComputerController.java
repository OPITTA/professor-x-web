/**
 * 欢迎浏览和修改代码，有任何想法可以email我
 */
package com.github.professor_x_web.controller;

import com.github.professor_x_web.constent.ComputerComponentType;
import com.github.professor_x_web.constent.Config;
import com.github.professor_x_web.model.Computer;
import com.github.professor_x_web.model.Cpu;
import com.github.professor_x_web.model.Dist;
import com.github.professor_x_web.model.Mem;
import com.github.professor_x_web.model.NetworkCard;
import com.github.professor_x_web.model.SolidDist;
import com.github.professor_x_web.service.ComputerService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author 510655387@qq.com
 */
@Controller
@RequestMapping("/computer/")
public class ComputerController {

    private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

    @Autowired
    private ComputerService computerService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listAction(HttpServletRequest httpServletRequest, Model model) {
        HttpSession httpSession = httpServletRequest.getSession();
        Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
        List<Computer> computers = computerService.getComputerByUserId(userId);
        model.addAttribute("computers", computers);
        model.addAttribute("CPU", ComputerComponentType.CPU);
        model.addAttribute("MEMORY", ComputerComponentType.MEMORY);
        model.addAttribute("DIST", ComputerComponentType.DIST);
        model.addAttribute("SOLID_DIST", ComputerComponentType.SOLID_DIST);
        model.addAttribute("NERWORK_CARD", ComputerComponentType.NERWORK_CARD);
        return "/computer/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addAction(HttpServletRequest httpServletRequest, Model model) {
        return "/computer/add";
    }

    @RequestMapping(value = "do_add", method = RequestMethod.POST)
    public String doAddAction(HttpServletRequest httpServletRequest, Model model) {
        String describe = httpServletRequest.getParameter("describe");
        if (describe != null && !describe.isEmpty()) {
            HttpSession httpSession = httpServletRequest.getSession();
            Integer userId = (Integer) httpSession.getAttribute(Config.USER_ID);
            Computer computer = new Computer();
            computer.setUserId(userId);
            computer.setDescribe(describe);
            computerService.createComputer(computer);
        }
        return "redirect:/computer/list";
    }

    @RequestMapping(value = "edit/{computer_id}/{computer_component_type_id}", method = RequestMethod.GET)
    public String editAction(@PathVariable("computer_id") Integer computerId, @PathVariable("computer_component_type_id") Integer computerComponentTypeId, Model model) {
        model.addAttribute("computerId", computerId);
        model.addAttribute("computerComponentTypeId", computerComponentTypeId);
        model.addAttribute("CPU", ComputerComponentType.CPU);
        model.addAttribute("MEMORY", ComputerComponentType.MEMORY);
        model.addAttribute("DIST", ComputerComponentType.DIST);
        model.addAttribute("SOLID_DIST", ComputerComponentType.SOLID_DIST);
        model.addAttribute("NERWORK_CARD", ComputerComponentType.NERWORK_CARD);
        ComputerComponentType computerComponentType = ComputerComponentType.fromInt(computerComponentTypeId);
        switch (computerComponentType) {
            case CPU: {
                Cpu cpu = computerService.getCpuByComputerId(computerId);
                model.addAttribute("Cpu", cpu);
                break;
            }
            case MEMORY: {
                Mem mem = computerService.getMemoryByComputerId(computerId);
                model.addAttribute("Mem", mem);
                break;
            }
            case DIST: {
                Dist dist = computerService.getDistByComputerId(computerId);
                model.addAttribute("Dist", dist);
                break;
            }
            case SOLID_DIST: {
                SolidDist solidDist = computerService.getSolidDistByComputerId(computerId);
                model.addAttribute("SolidDist", solidDist);
                break;
            }
            case NERWORK_CARD: {
                NetworkCard networkCard = computerService.getNerworkCardByComputerId(computerId);
                model.addAttribute("NetworkCard", networkCard);
                break;
            }
        }
        return "/computer/edit";
    }

    @RequestMapping(value = "do_edit", method = RequestMethod.POST)
    public String doEditAction(@Valid Cpu cpu, @Valid Mem mem, @Valid Dist dist, @Valid SolidDist solidDist, @Valid NetworkCard networkCard, @RequestParam("computerId") int computerId, @RequestParam("computerComponentTypeId") int computerComponentTypeId, BindingResult bindingResult) {
        ComputerComponentType computerComponentType = ComputerComponentType.fromInt(computerComponentTypeId);
        switch (computerComponentType) {
            case CPU: {
                if (!bindingResult.hasErrors() && computerId > 0 && computerComponentTypeId > 0) {
                    computerService.createCpu(computerId, cpu);
                }
                break;
            }
            case MEMORY: {
                if (!bindingResult.hasErrors() && computerId > 0 && computerComponentTypeId > 0) {
                    computerService.createMem(computerId, mem);
                }
                break;
            }
            case DIST: {
                if (!bindingResult.hasErrors() && computerId > 0 && computerComponentTypeId > 0) {
                    computerService.createDist(computerId, dist);
                }
                break;
            }
            case SOLID_DIST: {
                if (!bindingResult.hasErrors() && computerId > 0 && computerComponentTypeId > 0) {
                    computerService.createSolidDist(computerId, solidDist);
                }
                break;
            }
            case NERWORK_CARD: {
                if (!bindingResult.hasErrors() && computerId > 0 && computerComponentTypeId > 0) {
                    computerService.createNetworkCard(computerId, networkCard);
                }
                break;
            }
        }
        return String.format("redirect:/computer/edit/%s/%s", computerId, computerComponentTypeId);
    }
}
