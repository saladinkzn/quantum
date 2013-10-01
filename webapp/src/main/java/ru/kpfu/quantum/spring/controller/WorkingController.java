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


    @RequestMapping("/working")
    public String working(HttpServletRequest request) {
        List<ProjectGroup> groups = projectGroupRepository.findAllGroups();
        request.setAttribute("groups", groups);
        return "working/working";
    }

    @RequestMapping(value = "/working/project-list", method = RequestMethod.GET)
    public String getProjectDataList(HttpServletRequest request,
                                     @RequestParam Long groupId) {
        List<Project> projects = projectGroupRepository.findOneFetchChildren(groupId).getProjects();
        request.setAttribute("records", projects);
        return "working/data-list";
    }

    @RequestMapping(value = "/working/group-list", method = RequestMethod.GET)
    public String getGroupDataList(HttpServletRequest request) {
        List<ProjectGroup> groups = projectGroupRepository.findAllGroups();
        request.setAttribute("records", groups);
        return "working/data-list";
    }

    @ResponseBody
    @RequestMapping(value = "/working/create-group", method = RequestMethod.POST)
    public String createGroup(@RequestParam String groupName) {
        ProjectGroup group = new ProjectGroup(groupName);
        projectGroupRepository.save(group);
        return String.valueOf(group.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/working/create-project", method = RequestMethod.POST)
    public String createProject(@RequestParam Long groupId,
                              @RequestParam String projectName) {
        System.out.println(projectName);
        Project project = new Project(projectName, "");   //TODO использовать другой конструктор, когда будет реализован пользователь
        System.out.println(project.getName());
        Project saved = projectRepository.save(project);
        System.out.println(saved.getId());
        ProjectGroup group = projectGroupRepository.findOneFetchChildren(groupId);
        group.getProjects().add(saved);
        projectGroupRepository.save(group);
        return String.valueOf(project.getId());
    }

    @RequestMapping("/working/get-code")
    public String getCode(@RequestParam Long projectId){
        return projectRepository.findOne(projectId).getCode();
    }

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
