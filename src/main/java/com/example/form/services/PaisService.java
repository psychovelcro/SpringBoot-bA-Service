package com.example.form.services;

import java.util.List;

import com.example.form.models.domain.pais;

public interface PaisService {

	public List<pais> listar();

	public pais obtenerPorId(Integer id);

}
