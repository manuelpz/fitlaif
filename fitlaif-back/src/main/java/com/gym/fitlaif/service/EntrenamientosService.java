package com.gym.fitlaif.service;

import java.util.List;

import com.gym.fitlaif.domain.Entrenamientos;
import com.gym.fitlaif.dto.EntrenamientosDTO;

public interface EntrenamientosService {

	EntrenamientosDTO guardarEntrenamiento(Entrenamientos entrenamiento) throws Exception;
	List<EntrenamientosDTO> obtenerTodosLosEntrenamientos() throws Exception;
	EntrenamientosDTO obtenerEntrenamientos(String id) throws Exception;
	void eliminarEntrenamiento(String id) throws Exception;
	EntrenamientosDTO actualizarEntrenamiento(Entrenamientos entrenamiento) throws Exception;
}
