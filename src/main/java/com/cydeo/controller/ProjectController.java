package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }


    @GetMapping("/create")
    public String projectCreate(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("assignedManager", userService.findManagers());
        model.addAttribute("projectList",projectService.findAll());

        return "/project/create";
    }


    @PostMapping("/create")
    public String insertProject(@ModelAttribute ("project") ProjectDTO projectDTO){

        projectService.save(projectDTO);

        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable ("projectCode") String projectCode){

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable ("projectCode") String projectCode){

        projectService.completeProject(projectService.findById(projectCode));

        return "redirect:/project/create";
    }

//    @GetMapping("/complete/{projectCode}") // implementation of this -> //    void completeProject(String id); //this is what I did myself
//    public String completeProject(@PathVariable ("projectCode") String projectCode){
//
//        projectService.completeProject(projectCode);
//
//        return "redirect:/project/create";
//    }


    @GetMapping("/update/{projectCode}")
    public String editProject(Model model, @PathVariable ("projectCode") String projectCode){

        model.addAttribute("project",projectService.findById(projectCode));
        model.addAttribute("assignedManager", userService.findManagers());
        model.addAttribute("projectList",projectService.findAll());

        return "/project/update";
    }


    @PostMapping("/update")
    public String updateProject(@ModelAttribute ("project") ProjectDTO projectDTO){

        projectService.update(projectDTO);

        return "redirect:/project/create";
    }



}
