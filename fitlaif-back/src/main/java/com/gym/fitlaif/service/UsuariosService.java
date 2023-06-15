package com.gym.fitlaif.service;

import java.util.List;

import com.gym.fitlaif.domain.Usuarios;
import com.gym.fitlaif.dto.UsuariosDTO;

public interface UsuariosService {

	UsuariosDTO guardarUsuario(Usuarios usuario) throws Exception;
	List<UsuariosDTO> obtenerTodosLosUsuarios() throws Exception;
	UsuariosDTO obtenerUsuario(String id) throws Exception;
	UsuariosDTO obtenerUsuarioyContraseña(String id, String password) throws Exception;
	void eliminarUsuario(String id) throws Exception;
	UsuariosDTO actualizarUsuario(Usuarios ejercicio) throws Exception;
	void cerrarSesion(Usuarios usuario);
}
