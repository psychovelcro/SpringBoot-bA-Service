package com.example.form.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.form.models.domain.pais;

@Service
public class PaisServiceImpl implements PaisService {

	private List<pais> lista;

	public PaisServiceImpl() {
		this.lista = Arrays.asList(new pais(1, "ES", "España"), new pais(2, "MX", "Mexico"), new pais(3, "CL", "Chile"),
				new pais(4, "AR", "Argentina"), new pais(5, "PE", "Peru"), new pais(6, "CO", "Venezuela"),
				new pais(7, "VE", "Venezuela"));
	}

	@Override
	public List<pais> listar() {
		return lista;
	}

	@Override
	public pais obtenerPorId(Integer id) {

		pais resultado = null;
		for (pais pais : this.lista) {
			if (id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
