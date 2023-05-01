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
import com.gym.fitlaif.domain.Ejercicios;
import com.gym.fitlaif.dto.EjerciciosDTO;
import com.gym.fitlaif.exceptions.FitLaifConflictException;
import com.gym.fitlaif.exceptions.FitLaifNotFoundException;
import com.gym.fitlaif.mapper.EjerciciosMapper;
import com.gym.fitlaif.service.EjerciciosService;

@Service
public class EjerciciosServiceImpl implements EjerciciosService{
	
	private int CONFLICT_STATUS = 409;
	private int NOT_FOUND_STATUS = 404;
    private final Firestore firestore;

    @Autowired
    public EjerciciosServiceImpl(Firestore firestore) {
        this.firestore = firestore;
    }
    
    @Autowired
    EjerciciosMapper ejerciciosMapper;
    
	@Override
	public EjerciciosDTO guardarEjercicio(Ejercicios ejercicio) throws Exception {
		CollectionReference collection = firestore.collection("ejercicios");
        DocumentReference document = collection.document(ejercicio.getEjercicioId());
        ApiFuture<QuerySnapshot> querySnapshot = collection.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        for (QueryDocumentSnapshot documento : documents) {
        	Ejercicios ejercicioA = documento.toObject(Ejercicios.class);
        	if (ejercicioA.getEjercicioId().equals(ejercicio.getEjercicioId())) {
        		throw new FitLaifConflictException(CONFLICT_STATUS, "La ID de entrenamiento que estás intentando registrar ya se encuentra registrada");
        	}
        }
        persistirEjercicio(ejercicio, document);
        return ejerciciosMapper.toDTO(ejercicio);
		
	}

	@Override
	public EjerciciosDTO obtenerEjercicios(String id) throws Exception {
		DocumentReference document = firestore.collection("ejercicios").document(id);
		DocumentSnapshot documentSnapshot = document.get().get();
		if(documentSnapshot.exists()) {
			Ejercicios ejercicio = documentSnapshot.toObject(Ejercicios.class);
			return ejerciciosMapper.toDTO(ejercicio);			
		}
		else throw new FitLaifNotFoundException(NOT_FOUND_STATUS, "Este ejercicio no existe");
	}

	@Override
	public void eliminarEjercicio(String id) throws Exception {
		DocumentReference document = firestore.collection("ejercicios").document(id);
		if(encontrarPorId(id) != null) {
			document.delete();
		}
	    else throw new FitLaifNotFoundException(NOT_FOUND_STATUS, "No se encuentra el ejercicio que estás intentando eliminar");	
	}

	@Override
	public EjerciciosDTO actualizarEjercicio(Ejercicios ejercicio) throws Exception {
		DocumentReference document = firestore.collection("ejercicios").document(ejercicio.getEjercicioId());
		if(encontrarPorId(ejercicio.getEjercicioId()) != null) {
			persistirEjercicio(ejercicio, document);
			return ejerciciosMapper.toDTO(ejercicio);
		}
		else throw new FitLaifConflictException(CONFLICT_STATUS, "Ha habido un error inesperado al intentar actualizar este ejercicio, por favor, intentalo mas tarde");
	}
	
	public Ejercicios encontrarPorId(String id) throws Exception {
		Ejercicios ejercicio = null;
		CollectionReference collection = firestore.collection("ejercicios");
		ApiFuture<QuerySnapshot> querySnapshot = collection.get();
		 List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
	        for (QueryDocumentSnapshot documento : documents) {
	        	Ejercicios ejercicioA = documento.toObject(Ejercicios.class);
	        	if (ejercicioA.getEjercicioId().equals(id)) {
	        		ejercicio = ejercicioA;
	        		}
	        }
	        if(ejercicio == null) {
	        	return null;
	        }
	        else return ejercicio;
	}
	
	public void persistirEjercicio(Ejercicios ejercicio, DocumentReference document) {
		ObjectMapper mapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
		Map<String, Object> ejercicioMap = mapper.convertValue(ejercicio, Map.class);
        document.set(ejercicioMap);
	}

	@Override
	public List<EjerciciosDTO> obtenerTodosLosEjercicios() throws Exception {
		List<Ejercicios> ejercicios = new ArrayList<Ejercicios>();
		CollectionReference collection = firestore.collection("ejercicios");
		ApiFuture<QuerySnapshot> querySnapshot = collection.get();
		 List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
		 for (QueryDocumentSnapshot documento : documents) {
	        	Ejercicios ejercicioA = documento.toObject(Ejercicios.class);
	        	ejercicios.add(ejercicioA);
	        }
		return ejerciciosMapper.listToDTO(ejercicios);
	}
}
