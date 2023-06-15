package com.gym.fitlaif.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gym.fitlaif.domain.Usuarios;
import com.gym.fitlaif.dto.UsuariosDTO;
@Repository
public class UsuariosMapper {

	public UsuariosDTO toDTO(Usuarios usuario) {
		UsuariosDTO usuarioDTO = new UsuariosDTO();
		usuarioDTO.setUsuario(usuario.getUsuario());
		usuarioDTO.setPassword(usuario.getPassword());
		usuarioDTO.setIsLogged(usuario.getIsLogged());
		return usuarioDTO;
	}
	
	public List<UsuariosDTO> listToDTO(List<Usuarios> usuarios) {
		ArrayList<UsuariosDTO> usuariosDTO = new ArrayList<UsuariosDTO>();
		for(Usuarios usuario : usuarios) {
			UsuariosDTO usuarioDTO = new UsuariosDTO();
			usuarioDTO.setUsuario(usuario.getUsuario());
			usuarioDTO.setPassword(usuario.getPassword());
			usuarioDTO.setIsLogged(usuario.getIsLogged());
		
			usuariosDTO.add(usuarioDTO);
		}
		return usuariosDTO;
	}
}
