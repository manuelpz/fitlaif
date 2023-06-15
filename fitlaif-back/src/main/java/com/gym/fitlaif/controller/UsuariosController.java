package com.gym.fitlaif.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.fitlaif.domain.Usuarios;
import com.gym.fitlaif.dto.UsuariosDTO;
import com.gym.fitlaif.service.UsuariosService;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	@Autowired
	UsuariosService usuariosService;
	
	@GetMapping("/{id}")
	private UsuariosDTO obtenerUsuario(@PathVariable String id) throws Exception {
		return usuariosService.obtenerUsuario(id);
	}
	
	@GetMapping("/{id}/{password}")
	private UsuariosDTO obtenerUsuarioyContraseña(@PathVariable String id, @PathVariable String password) throws Exception {
		return usuariosService.obtenerUsuarioyContraseña(id, password);
	}
	
	@GetMapping
	private List<UsuariosDTO> obtenerTodosLosUsuarios() throws Exception{
		return usuariosService.obtenerTodosLosUsuarios();
	}

	@PostMapping("/guardar")
	private UsuariosDTO guardarEjercicio(@RequestBody Usuarios usuario) throws Exception{
		return usuariosService.guardarUsuario(usuario);
	}
	
	@PostMapping("/cerrarSesion")
	private void cerrarSesion(@RequestBody Usuarios usuario) throws Exception{
		 usuariosService.cerrarSesion(usuario);
	}
	
	@DeleteMapping("/eliminar/{id}")
	private void borrarEjericicio(@PathVariable String id) throws Exception {
		usuariosService.eliminarUsuario(id);
	}
	
	@PutMapping("/actualizar")
	private UsuariosDTO actualizarEjercicio(@RequestBody Usuarios usuario) throws Exception {
		return usuariosService.actualizarUsuario(usuario);
	}
}
