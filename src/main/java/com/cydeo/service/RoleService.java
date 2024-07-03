package com.cydeo.service;

import com.cydeo.dto.RoleDTO;

import java.util.List;

public interface RoleService extends CrudService<RoleDTO,Long>{

    @Override
    RoleDTO save(RoleDTO object);

    @Override
    RoleDTO findById(Long id);

    @Override
    List<RoleDTO> findAll();

    @Override
    void deleteById(Long id);
}
