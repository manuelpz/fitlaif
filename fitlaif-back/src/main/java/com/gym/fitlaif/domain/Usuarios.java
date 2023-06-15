package com.gym.fitlaif.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@Getter
@Setter
@NoArgsConstructor
public class Usuarios {

	@Id
	private String usuario;
	
	private String password;
	
	private Boolean isLogged;
}
