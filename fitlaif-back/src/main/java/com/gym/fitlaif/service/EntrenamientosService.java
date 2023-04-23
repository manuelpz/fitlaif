package com.gym.fitlaif.service;

import java.util.List;

import com.gym.fitlaif.domain.Entrenamientos;
import com.gym.fitlaif.dto.EntrenamientosDTO;

public interface EntrenamientosService {

	public EntrenamientosDTO guardarEntrenamiento(Entrenamientos entrenamiento) throws Exception; 
	public List<EntrenamientosDTO> obtenerTodosLosEntrenamientos() throws Exception;
	public EntrenamientosDTO obtenerEntrenamientos(String id) throws Exception;
	public void eliminarEntrenamiento(String id) throws Exception;
	public EntrenamientosDTO actualizarEntrenamiento(Entrenamientos entrenamiento) throws Exception;
}
