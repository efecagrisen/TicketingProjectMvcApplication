package com.cydeo.service;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO, String> {

    @Override
    UserDTO save(UserDTO object);

    @Override
    UserDTO findById(String id);

    @Override
    List<UserDTO> findAll();

    @Override
    void deleteById(String id);
}
