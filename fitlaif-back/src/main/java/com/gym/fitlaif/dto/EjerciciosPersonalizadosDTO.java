package com.gym.fitlaif.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class EjerciciosPersonalizadosDTO {
	private String ejercicioId;
	private String ejercicio;
	private List<String> series;
	private List<String> repeticiones;
	private String usuario;
	private String pr;
}
