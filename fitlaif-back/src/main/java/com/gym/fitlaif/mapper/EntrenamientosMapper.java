package com.gym.fitlaif.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gym.fitlaif.domain.Entrenamientos;
import com.gym.fitlaif.dto.EntrenamientosDTO;
@Repository
public class EntrenamientosMapper {

	public EntrenamientosDTO toDTO(Entrenamientos entrenamiento) {
		EntrenamientosDTO entrenamientoDTO = new EntrenamientosDTO();
		entrenamientoDTO.setEntrenamientoId(entrenamiento.getEntrenamientoId());
		entrenamientoDTO.setMusculo(entrenamiento.getMusculo());
		entrenamientoDTO.setImg(entrenamiento.getImg());
		entrenamientoDTO.setHashtag(entrenamiento.getHashtag());
		return entrenamientoDTO;
	}
	
	public List<EntrenamientosDTO> listToDTO (List<Entrenamientos> entrenamientos) {
		ArrayList<EntrenamientosDTO> entrenamientosDTO = new ArrayList<EntrenamientosDTO>();
		for(Entrenamientos entrenamiento : entrenamientos) {
			EntrenamientosDTO entrenamientoDTO = new EntrenamientosDTO();
			entrenamientoDTO.setEntrenamientoId(entrenamiento.getEntrenamientoId());
			entrenamientoDTO.setMusculo(entrenamiento.getMusculo());
			entrenamientoDTO.setImg(entrenamiento.getImg());
			entrenamientoDTO.setHashtag(entrenamiento.getHashtag());
			entrenamientosDTO.add(entrenamientoDTO);
		}
		return entrenamientosDTO;		
	}
}
