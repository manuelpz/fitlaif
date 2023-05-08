package com.gym.fitlaif.service;

import java.util.List;

import com.gym.fitlaif.domain.Ejercicios;
import com.gym.fitlaif.dto.EjerciciosDTO;

public interface EjerciciosService {

	EjerciciosDTO guardarEjercicio(Ejercicios ejercicio) throws Exception;
	List<EjerciciosDTO> obtenerTodosLosEjercicios() throws Exception;
	EjerciciosDTO obtenerEjercicios(String id) throws Exception;
	void eliminarEjercicio(String id) throws Exception;
	EjerciciosDTO actualizarEjercicio(Ejercicios ejercicio) throws Exception;
}
