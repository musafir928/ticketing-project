package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceIMPL extends AbstractMapServices<Long, RoleDTO> implements RoleService {
    @Override
    public RoleDTO save(RoleDTO object) {
        return super.save(object.getId(), object);
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void update(RoleDTO role) {
        super.update(role.getId(), role);
    }
}
