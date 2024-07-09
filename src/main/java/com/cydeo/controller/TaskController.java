package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }


    @GetMapping("/create")
    public String createTask(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("assignedEmployee",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/create";
    }

    @PostMapping("/create")
    public String insertTask(@ModelAttribute ("task") TaskDTO taskDTO){

        taskService.save(taskDTO);

        return "redirect:/task/create";
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable ("taskId") Long taskId){

        taskService.deleteById(taskId);

        return "redirect:/task/create";

    }


    @GetMapping("/update/{taskId}")
    public String ediTask(@PathVariable ("taskId") Long taskId, Model model){

        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("assignedEmployee",userService.findEmployees());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/update";
    }


//    @PostMapping("/update/{taskId}")
//    public String updateTask(@ModelAttribute ("task") TaskDTO taskDTO, @PathVariable ("taskId") Long taskId){
//
//        taskService.update(taskDTO);
//
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}")
    public String updateTask(TaskDTO taskDTO){

        taskService.update(taskDTO);

        return "redirect:/task/create";
    }



}
