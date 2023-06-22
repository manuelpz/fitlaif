package com.gym.fitlaif.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.gym.fitlaif.dto.EjerciciosPersonalizadosDTO;
import com.gym.fitlaif.dto.SeriesRepeticionesDTO;
@Repository
public class SeriesRepeticionesMapper {

	public List<SeriesRepeticionesDTO> toSeriesRepeticiones(EjerciciosPersonalizadosDTO ejercicio) {
	    ArrayList<SeriesRepeticionesDTO> seriesRepeticionesDTO = new ArrayList<SeriesRepeticionesDTO>();

	    List<String> series = ejercicio.getSeries();
	    List<String> repeticiones = ejercicio.getRepeticiones();
	    List<String> peso = ejercicio.getPeso();

	    int size = Math.min(series.size(), repeticiones.size());
	    for (int index = 0; index < size; index++) {
	        SeriesRepeticionesDTO ser = new SeriesRepeticionesDTO();
	        ser.setSeries(series.get(index));
	        ser.setRepeticiones(repeticiones.get(index));
	        ser.setPeso(peso.get(index));
	        seriesRepeticionesDTO.add(ser);
	    }

	    return seriesRepeticionesDTO;
	}
}
