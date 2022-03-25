package com.cydeo.service;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;

public interface RoleService {
    /**
     * functionalities:
     * save, findById, delete, findAll
     * */

    RoleDTO save(RoleDTO role);
    RoleDTO findById(Long id);
    List<RoleDTO> findAll();
    void delete(RoleDTO role);
    void deleteById(Long id);
}
