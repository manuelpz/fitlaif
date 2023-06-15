package com.gym.fitlaif.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class UsuariosDTO {
	private String usuario;
	private String password;
	private Boolean isLogged;
}
