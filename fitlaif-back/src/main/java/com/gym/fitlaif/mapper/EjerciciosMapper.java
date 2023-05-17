package com.gym.fitlaif.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gym.fitlaif.domain.Ejercicios;
import com.gym.fitlaif.domain.Entrenamientos;
import com.gym.fitlaif.dto.EjerciciosDTO;
import com.gym.fitlaif.dto.EntrenamientosDTO;
@Repository
public class EjerciciosMapper {

	public EjerciciosDTO toDTO(Ejercicios ejercicio) {
		EjerciciosDTO ejercicioDTO = new EjerciciosDTO();
		ejercicioDTO.setEjercicioId(ejercicio.getEjercicioId());
		ejercicioDTO.setEjercicio(ejercicio.getEjercicio());
		ejercicioDTO.setMusculo(ejercicio.getMusculo());
		ejercicioDTO.setRepeticiones(ejercicio.getRepeticiones());
		ejercicioDTO.setSeries(ejercicio.getSeries());
		ejercicioDTO.setImg(ejercicio.getImg());
		return ejercicioDTO;
	}
	
	public List<EjerciciosDTO> listToDTO(List<Ejercicios> ejercicios) {
		ArrayList<EjerciciosDTO> ejerciciosDTO = new ArrayList<EjerciciosDTO>();
		for(Ejercicios ejercicio : ejercicios) {
		EjerciciosDTO ejercicioDTO = new EjerciciosDTO();
		ejercicioDTO.setEjercicioId(ejercicio.getEjercicioId());
		ejercicioDTO.setEjercicio(ejercicio.getEjercicio());
		ejercicioDTO.setMusculo(ejercicio.getMusculo());
		ejercicioDTO.setRepeticiones(ejercicio.getRepeticiones());
		ejercicioDTO.setSeries(ejercicio.getSeries());
		ejercicioDTO.setImg(ejercicio.getImg());
		ejerciciosDTO.add(ejercicioDTO);
		}
		return ejerciciosDTO;
	}
}
