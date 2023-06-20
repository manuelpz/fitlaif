package com.gym.fitlaif.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gym.fitlaif.domain.EjerciciosPersonalizados;
import com.gym.fitlaif.dto.EjerciciosPersonalizadosDTO;
@Repository
public class EjerciciosPersonalizadosMapper {

	public EjerciciosPersonalizadosDTO toDTO(EjerciciosPersonalizados ejercicio) {
		EjerciciosPersonalizadosDTO ejercicioPersonalizadoDTO = new EjerciciosPersonalizadosDTO();
		ejercicioPersonalizadoDTO.setEjercicioId(ejercicio.getEjercicioId());
		ejercicioPersonalizadoDTO.setEjercicio(ejercicio.getEjercicio());
		ejercicioPersonalizadoDTO.setUsuario(ejercicio.getUsuario());
		ejercicioPersonalizadoDTO.setRepeticiones(ejercicio.getRepeticiones());
		ejercicioPersonalizadoDTO.setSeries(ejercicio.getSeries());
		ejercicioPersonalizadoDTO.setPr(ejercicio.getPr());
		return ejercicioPersonalizadoDTO;
	}
	
	public List<EjerciciosPersonalizadosDTO> listToDTO(List<EjerciciosPersonalizados> ejerciciosPersonalizados) {
		ArrayList<EjerciciosPersonalizadosDTO> ejerciciosPersonalizadosDTO = new ArrayList<EjerciciosPersonalizadosDTO>();
		for(EjerciciosPersonalizados ejercicio : ejerciciosPersonalizados) {
		EjerciciosPersonalizadosDTO ejercicioDTO = new EjerciciosPersonalizadosDTO();
		ejercicioDTO.setEjercicioId(ejercicio.getEjercicioId());
		ejercicioDTO.setEjercicio(ejercicio.getEjercicio());
		ejercicioDTO.setUsuario(ejercicio.getUsuario());
		ejercicioDTO.setRepeticiones(ejercicio.getRepeticiones());
		ejercicioDTO.setSeries(ejercicio.getSeries());
		ejercicioDTO.setPr(ejercicio.getPr());
		ejerciciosPersonalizadosDTO.add(ejercicioDTO);
		}
		return ejerciciosPersonalizadosDTO;
	}
}
