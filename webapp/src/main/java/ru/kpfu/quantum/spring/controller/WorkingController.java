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


    enum Filters {All, Working, Archive}

    @RequestMapping("/working")
    public String working(HttpServletRequest request) {
        List<ProjectGroup> groups = projectGroupRepository.findAllGroups();
        request.setAttribute("groups", groups);
        request.setAttribute("filters", Filters.values());
        return "working/working";
    }

    @RequestMapping(value = "/working/project-list", method = RequestMethod.GET)
    public String getProjectDataList(HttpServletRequest request,
                                     @RequestParam Long groupId,
                                     @RequestParam String filter) {
        System.out.println("PrePreFirst");
        List<Project> projects;
        System.out.println("PreFirst");
        if(filter.equals("All")){
            System.out.println("First");
            projects = projectGroupRepository.findOneFetchChildren(groupId).getProjects();
            System.out.println("Second");
        }
        else{
            if(filter.equals("Working")){
                System.out.println("Third");
                projects = projectRepository.findAllByArchive(groupId, false);
            }
            else{
                System.out.println("4th");
                projects = projectRepository.findAllByArchive(groupId, true);
            }
        }
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


    @RequestMapping("/working/get-project")
    public String getCode(HttpServletRequest request,
                          @RequestParam Long projectId){
        Project project = projectRepository.findOne(projectId);
        request.setAttribute("project", project);
        return "working/project";
    }

    @ResponseBody
    @RequestMapping(value = "/working/save", method = RequestMethod.POST)
    public String save(@RequestParam Long projectId,
                          @RequestParam String code)
    {
        Project project = projectRepository.findOne(projectId);
        project.setCode(code);
        project.setCalculated(false);
        projectRepository.save(project);
        return "";
    }

    @RequestMapping(value = "/working/calculate", method = RequestMethod.POST)
    public String calculate(HttpServletRequest request,
                            @RequestParam Long projectId,
                            @RequestParam String code){
        Project project = projectRepository.findOne(projectId);
        project.setCode(code);
        project.setCalculated(true);
        //TODO в дальнейшем сделать обработку кода.
        projectRepository.save(project);
        return "working/result";
    }

    @ResponseBody
    @RequestMapping(value = "/working/archive", method = RequestMethod.POST)
    public String archive(@RequestParam Long projectId)
    {
        Project project = projectRepository.findOne(projectId);
        project.setArchive(true);
        projectRepository.save(project);
        return "";
    }
}