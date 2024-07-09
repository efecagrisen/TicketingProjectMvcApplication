package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(@Lazy TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO object) {

        if (object.getProjectStatus()==null){
        object.setProjectStatus(Status.OPEN);
        }

        return super.save(object.getProjectCode(),object);
    }

    @Override
    public ProjectDTO findById(String id) {
        return super.findById(id);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String id) {
        super.deleteById(id);
    }

    @Override
    public void update(ProjectDTO projectDTO) {

        if (projectDTO.getProjectStatus()==null) {
            projectDTO.setProjectStatus(findById(projectDTO.getProjectCode()).getProjectStatus());
        }
        super.update(projectDTO.getProjectCode(),projectDTO);
    }

    @Override
    public void completeProject(ProjectDTO projectDTO) {

        super.findById(projectDTO.getProjectCode()).setProjectStatus(Status.COMPLETE);
    }


//    @Override // this is what I did contrary to the lesson
//    public void completeProject(String id) {
//        super.findById(id).setProjectStatus(Status.COMPLETE);
//    }


    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList = super.findAll()
                .stream()
                .filter(projectDTO -> projectDTO.getAssignedManager().equals(manager))
                .map(project -> {

                    List<TaskDTO> completedTasks = taskService.findTasksByManager(manager);

                    int completeTaskCounts = (int) completedTasks.stream()
                            .filter(taskDTO -> taskDTO.getProject().equals(project)
                                    &&
                            taskDTO.getTaskStatus() ==Status.COMPLETE) //manager can have more than one project, so we need to filter the project we ask for its tasks first!!

                            .count(); //count returns long, so we need to cast it to int

                    int unfinishedTaskCounts = (int) completedTasks.stream()
                            .filter(taskDTO -> taskDTO.getProject().equals(project)
                                    &&
                                    taskDTO.getTaskStatus() !=Status.COMPLETE) //manager can have more than one project, so we need to filter the project we ask for its tasks first!!

                            .count();

                    project.setCompleteTaskCounts(completeTaskCounts);
                    project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                    return project;
                })

                .collect(Collectors.toList());



        return projectList;
    }


}

