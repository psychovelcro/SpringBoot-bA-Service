package com.example.form.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.form.models.domain.Usuario;
import com.example.form.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Jon");
		usuario.setApellido("Doe");
		usuario.setIdentificador("123.456.789-k");
		model.addAttribute("titulo", "Formulario Usuario");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {

		validador.validate(usuario, result);
		model.addAttribute("titulo", "Resultado form");

		if (result.hasErrors()) {

//			Map<String, String> errores = new HashMap<>();
//			result.getFieldErrors().forEach(err -> {
//				errores.put(err.getField(),
//						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//			});
//			model.addAttribute("error", errores);

			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete();

		return "resultado";

	}

}
