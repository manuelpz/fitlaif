package com.gym.fitlaif.service;

import java.util.List;

import com.gym.fitlaif.domain.Ejercicios;
import com.gym.fitlaif.dto.EjerciciosDTO;

public interface EjerciciosService {

	public EjerciciosDTO guardarEjercicio(Ejercicios ejercicio) throws Exception; 
	public List<EjerciciosDTO> obtenerTodosLosEjercicios() throws Exception;
	public EjerciciosDTO obtenerEjercicios(String id) throws Exception;
	public void eliminarEjercicio(String id) throws Exception;
	public EjerciciosDTO actualizarEjercicio(Ejercicios ejercicio) throws Exception;
}
