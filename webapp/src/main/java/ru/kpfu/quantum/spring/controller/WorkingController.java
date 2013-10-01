package ru.kpfu.quantum.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.quantum.spring.entities.Project;
import ru.kpfu.quantum.spring.entities.ProjectGroup;
import ru.kpfu.quantum.spring.repository.ProjectGroupRepository;
import ru.kpfu.quantum.spring.repository.ProjectRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class WorkingController {


    @Autowired
    private ProjectGroupRepository projectGroupRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @ResponseBody
    @RequestMapping(value = "/working/create-group", method = RequestMethod.POST)
    public String createGroup(@RequestParam String groupName) {
        ProjectGroup group = new ProjectGroup(groupName);
        projectGroupRepository.save(group);
        return String.valueOf(group.getId());
    }

    @ResponseBody
    @RequestMapping("/working/get-code")
    public String getCode(@RequestParam Long projectId){
        System.out.println(projectRepository.findOne(projectId).getCode());
        System.out.println(projectRepository.findOne(projectId).getName());
        return projectRepository.findOne(projectId).getCode();
    }

    @ResponseBody
    @RequestMapping(value = "/working/set-code", method = RequestMethod.POST)
    public String setCode(@RequestParam Long projectId,
                          @RequestParam String code)
    {
        Project project = projectRepository.findOne(projectId);
        project.setCode(code);
        projectRepository.save(project);
        return "";
    }
}