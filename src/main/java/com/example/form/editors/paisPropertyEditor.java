package com.example.form.editors;

import java.beans.PropertyEditorSupport;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.form.services.PaisService;

@Component
public class paisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private PaisService service;

	@Override
	public void setAsText(String idString) throws IllegalArgumentException {
		if (idString != null && idString.length() > 0) {

			try {
				Integer id = Integer.parseInt(idString);
				this.setValue(service.obtenerPorId(id));
			} catch (NumberFormatException e) {
				setValue(null);
			}
		} else {
			setValue(null);

		}
	}

}
