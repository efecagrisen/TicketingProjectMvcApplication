package com.cydeo.service;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO, String> {

    @Override
    UserDTO save(UserDTO user);

    @Override
    UserDTO findById(String username);

    @Override
    List<UserDTO> findAll();

    @Override
    void deleteById(String username);
}
