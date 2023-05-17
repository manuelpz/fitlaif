package com.gym.fitlaif.dto;


import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class EntrenamientosDTO {
	
	@Id
	private String entrenamientoId;
	
	@NotBlank
	private String musculo;
	
	private String img;
	
	private String prioridad;
	
	private String descripcion;
}
