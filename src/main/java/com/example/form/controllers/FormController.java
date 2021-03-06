package com.example.form.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.form.editors.NombreMayusculaEditor;
import com.example.form.editors.RolesEditor;
import com.example.form.editors.paisPropertyEditor;
import com.example.form.models.domain.Role;
import com.example.form.models.domain.Usuario;
import com.example.form.models.domain.pais;
import com.example.form.services.PaisService;
import com.example.form.services.RoleService;
import com.example.form.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private paisPropertyEditor paisEditor;

	@Autowired
	private RolesEditor roleEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());

		binder.registerCustomEditor(pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}

	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre", "Mujer");
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return this.roleService.listar();
	}

	@ModelAttribute("listaPaises")
	public List<pais> listaPaises() {
		return paisService.listar();
	}

	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");

		return roles;
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("Espa??a", "M??xico", "Chile", "Argentina", "Per??", "Colombia", "Venezuela");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "Espa??a");
		paises.put("MX", "M??xico");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Per??");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");
		return paises;
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("John");
		usuario.setApellido("Doe");
		usuario.setIdentificador("12.456.789-K");
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Algun valor secreto ***");
		usuario.setPais(new pais(3, "CL", "Chile"));
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));
		
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {

		// validador.validate(usuario, result);

		model.addAttribute("titulo", "Resultado form");

		if (result.hasErrors()) {

			return "form";
		}

		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
	}

}