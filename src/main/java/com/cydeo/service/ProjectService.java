package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO,String> {

//    void completeProject(String id); //this is what I did myself
//    By using this approach:
//    The controller remains simple and only calls the service method.
//    The service handles both finding the project and setting its status.
//    Error handling (e.g., project not found) is centralized in the service layer, making it more robust and reusable.


    void completeProject(ProjectDTO projectDTO); // this is what is shown in the lesson
}
