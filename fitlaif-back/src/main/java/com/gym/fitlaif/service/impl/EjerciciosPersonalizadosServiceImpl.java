package com.gym.fitlaif.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.gym.fitlaif.domain.EjerciciosPersonalizados;
import com.gym.fitlaif.dto.EjerciciosPersonalizadosDTO;
import com.gym.fitlaif.dto.SeriesRepeticionesDTO;
import com.gym.fitlaif.exceptions.FitLaifConflictException;
import com.gym.fitlaif.exceptions.FitLaifNotFoundException;
import com.gym.fitlaif.mapper.EjerciciosPersonalizadosMapper;
import com.gym.fitlaif.mapper.SeriesRepeticionesMapper;
import com.gym.fitlaif.service.EjerciciosPersonalizadosService;

@Service
public class EjerciciosPersonalizadosServiceImpl implements EjerciciosPersonalizadosService{
	
	private final int CONFLICT_STATUS = 409;
	private final int NOT_FOUND_STATUS = 404;
    private final Firestore firestore;

    public EjerciciosPersonalizadosServiceImpl(Firestore firestore) {
        this.firestore = firestore;
    }
    
    @Autowired
    EjerciciosPersonalizadosMapper ejerciciosMapper;
    
    @Autowired
    SeriesRepeticionesMapper seriesRepeticionesMapper;
    
	@Override
	public EjerciciosPersonalizadosDTO guardarEjercicio(EjerciciosPersonalizados ejercicio) throws Exception {
		CollectionReference collection = firestore.collection("ejercicios-personalizados");
        DocumentReference document = collection.document(ejercicio.getEjercicioId());
        ApiFuture<QuerySnapshot> querySnapshot = collection.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        for (QueryDocumentSnapshot documento : documents) {
        	EjerciciosPersonalizados ejercicioA = documento.toObject(EjerciciosPersonalizados.class);
        	if (ejercicioA.getEjercicioId().equals(ejercicio.getEjercicioId())) {
        		throw new FitLaifConflictException(CONFLICT_STATUS, "La ID de entrenamiento que estás intentando registrar ya se encuentra registrada");
        	}
        }
        persistirEjercicio(ejercicio, document);
        return ejerciciosMapper.toDTO(ejercicio);
		
	}

	@Override
	public EjerciciosPersonalizadosDTO obtenerEjercicios(String id) throws Exception {
		DocumentReference document = firestore.collection("ejercicios-personalizados").document(id);
		DocumentSnapshot documentSnapshot = document.get().get();
		if(documentSnapshot.exists()) {
			EjerciciosPersonalizados ejercicio = documentSnapshot.toObject(EjerciciosPersonalizados.class);
			assert ejercicio != null;
			return ejerciciosMapper.toDTO(ejercicio);
		}
		else throw new FitLaifNotFoundException(NOT_FOUND_STATUS, "Este ejercicio no existe");
	}

	@Override
	public void eliminarEjercicio(String id) throws Exception {
		DocumentReference document = firestore.collection("ejercicios-personalizados").document(id);
		if(encontrarPorId(id) != null) {
			document.delete();
		}
	    else throw new FitLaifNotFoundException(NOT_FOUND_STATUS, "No se encuentra el ejercicio que estás intentando eliminar");	
	}

	@Override
	public EjerciciosPersonalizadosDTO actualizarEjercicio(EjerciciosPersonalizados ejercicio) throws Exception {
		DocumentReference document = firestore.collection("ejercicios-personalizados").document(ejercicio.getEjercicioId());
		if(encontrarPorId(ejercicio.getEjercicioId()) != null) {
			persistirEjercicio(ejercicio, document);
			return ejerciciosMapper.toDTO(ejercicio);
		}
		else throw new FitLaifConflictException(CONFLICT_STATUS, "Ha habido un error inesperado al intentar actualizar este ejercicio, por favor, intentalo mas tarde");
	}
	
	public EjerciciosPersonalizados encontrarPorId(String id) throws Exception {
		EjerciciosPersonalizados ejercicio = null;
		CollectionReference collection = firestore.collection("ejercicios-personalizados");
		ApiFuture<QuerySnapshot> querySnapshot = collection.get();
		 List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
	        for (QueryDocumentSnapshot documento : documents) {
	        	EjerciciosPersonalizados ejercicioA = documento.toObject(EjerciciosPersonalizados.class);
	        	if (ejercicioA.getEjercicioId().equals(id)) {
	        		ejercicio = ejercicioA;
	        		}
	        }
	        if(ejercicio != null) {
	        	return ejercicio;
	        }
	        else throw new FitLaifNotFoundException(NOT_FOUND_STATUS, "Este ejercicio no existe");
	}
	
	public void persistirEjercicio(EjerciciosPersonalizados ejercicio, DocumentReference document) {
		ObjectMapper mapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
		Map<String, Object> ejercicioMap = mapper.convertValue(ejercicio, Map.class);
        document.set(ejercicioMap);
	}

	@Override
	public List<EjerciciosPersonalizadosDTO> obtenerTodosLosEjercicios() throws Exception {
		List<EjerciciosPersonalizados> ejercicios = new ArrayList<>();
		CollectionReference collection = firestore.collection("ejercicios-personalizados");
		ApiFuture<QuerySnapshot> querySnapshot = collection.get();
		 List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
		 for (QueryDocumentSnapshot documento : documents) {
	        	EjerciciosPersonalizados ejercicioA = documento.toObject(EjerciciosPersonalizados.class);
	        	ejercicios.add(ejercicioA);
	        }
		return ejerciciosMapper.listToDTO(ejercicios);
	}

	@Override
	public List<SeriesRepeticionesDTO> obtenerSeriesYRepeticiones(String id)
			throws Exception {
		EjerciciosPersonalizados ejercicioPersonalizadoEncontrado = encontrarPorId(id);
		List<SeriesRepeticionesDTO> seriesRepes = seriesRepeticionesMapper.toSeriesRepeticiones(ejerciciosMapper.toDTO(ejercicioPersonalizadoEncontrado));
		return seriesRepes;
	}
}
