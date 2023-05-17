package com.gym.fitlaif.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class EjerciciosDTO {
	private String ejercicioId;
	private String ejercicio;
	private String musculo;
	private String series;
	private String repeticiones;
	private String img;
}
