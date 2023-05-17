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
public class Ejercicios {

	@Id
	private String ejercicioId;
	
	private String ejercicio;
	
	private String musculo;
	
	private String repeticiones;
	
	private String series;
	
	private String img;
}
