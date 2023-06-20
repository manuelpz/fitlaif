package com.gym.fitlaif.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@Getter
@Setter
@NoArgsConstructor
public class EjerciciosPersonalizados {

	@Id
	private String ejercicioId;
	
	private String ejercicio;
	
	private List<String> repeticiones;
	
	private List<String> series;
	
	private String usuario;
	
	private String pr;
}
