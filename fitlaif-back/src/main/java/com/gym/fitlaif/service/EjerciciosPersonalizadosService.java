package com.gym.fitlaif.service;

import java.util.List;

import com.gym.fitlaif.domain.EjerciciosPersonalizados;
import com.gym.fitlaif.dto.EjerciciosPersonalizadosDTO;
import com.gym.fitlaif.dto.SeriesRepeticionesDTO;

public interface EjerciciosPersonalizadosService {

	EjerciciosPersonalizadosDTO guardarEjercicio(EjerciciosPersonalizados ejercicio) throws Exception;
	List<EjerciciosPersonalizadosDTO> obtenerTodosLosEjercicios() throws Exception;
	EjerciciosPersonalizadosDTO obtenerEjercicios(String id) throws Exception;
	void eliminarEjercicio(String id) throws Exception;
	EjerciciosPersonalizadosDTO actualizarEjercicio(EjerciciosPersonalizados ejercicio) throws Exception;
	List<SeriesRepeticionesDTO> obtenerSeriesYRepeticiones(EjerciciosPersonalizadosDTO ejercicioPersonalizado) throws Exception;
}
