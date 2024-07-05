package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService extends CrudService<ProjectDTO,String>{
    @Override
    ProjectDTO save(ProjectDTO object);

    @Override
    ProjectDTO findById(String s);

    @Override
    List<ProjectDTO> findAll();

    @Override
    void deleteById(String s);

    @Override
    void update(ProjectDTO object);
}
