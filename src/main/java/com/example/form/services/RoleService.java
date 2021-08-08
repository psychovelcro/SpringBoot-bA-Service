package com.example.form.services;

import java.util.List;

import com.example.form.models.domain.Role;

public interface RoleService {

	public List<Role> listar();

	public Role obtenerPorId(Integer id);

}
