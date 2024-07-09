package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO,Long> implements TaskService  {

    private final ProjectService projectService;
    private final UserService userService;

    public TaskServiceImpl(@Lazy ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }


    @Override
    public TaskDTO save(TaskDTO object) {
        if (object.getId()==null){
            object.setId(UUID.randomUUID().getMostSignificantBits());
        }
        if (object.getTaskStatus()==null){
            object.setTaskStatus(Status.OPEN);
        }
        if (object.getAssignedDate()==null){
            object.setAssignedDate(LocalDate.now());
        }
        return super.save(object.getId(),object);
    }

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void update(TaskDTO object) {

        TaskDTO foundTask = findById(object.getId());

        object.setTaskStatus(foundTask.getTaskStatus());
        object.setAssignedDate(foundTask.getAssignedDate());

        super.update(object.getId(),object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {
        return findAll()
                .stream()
                .filter(taskDTO -> taskDTO.getProject().getAssignedManager().equals(manager))
                .collect(Collectors.toList());
    }
}
