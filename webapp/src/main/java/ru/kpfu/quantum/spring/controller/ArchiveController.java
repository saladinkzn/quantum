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
import ru.kpfu.quantum.spring.entities.User;
import ru.kpfu.quantum.spring.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ArchiveController {


    @Autowired
    private ProjectGroupRepository projectGroupRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @RequestMapping("/archive")
    public String archive(HttpServletRequest request) {
        User currentUser = UserUtils.getUserFromSession(request);
        List<ProjectGroup> groups = projectGroupRepository.findAllGroupsOwnByUser(currentUser);
        request.setAttribute("groups", groups);
        return "archive/archive";
    }


    @RequestMapping(value = "/archive/my_arch_proj_list", method = RequestMethod.GET)
    public String getProjectDataList(HttpServletRequest request,
                                     @RequestParam Long groupId) {

        List<Project> projects = projectRepository.findAllByArchive(groupId, true);
        request.setAttribute("records", projects);
        return "archive/proj-list";
    }

    @RequestMapping("/archive/get-project")
    public String getProj(HttpServletRequest request,
                          @RequestParam Long projectId){
        Project project = projectRepository.findOne(projectId);
        project.setCode(project.getCode().replace("\n", "<br/>"));
        request.setAttribute("project", project);
        return "archive/project";
    }

}