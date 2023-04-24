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
import com.gym.fitlaif.domain.Entrenamientos;
import com.gym.fitlaif.dto.EntrenamientosDTO;
import com.gym.fitlaif.exceptions.EntrenamientoConflictException;
import com.gym.fitlaif.mapper.EntrenamientosMapper;
import com.gym.fitlaif.service.EntrenamientosService;

@Service
public class EntrenamientosServiceImpl implements EntrenamientosService{
	
	private int CONFLICT_STATUS = 409;
	private int NOT_FOUND_STATUS = 409;
    private final Firestore firestore;

    @Autowired
    public EntrenamientosServiceImpl(Firestore firestore) {
        this.firestore = firestore;
    }
    
    @Autowired
    EntrenamientosMapper entrenamientosMapper;
    
	@Override
	public EntrenamientosDTO guardarEntrenamiento(Entrenamientos entrenamiento) throws Exception {
		CollectionReference collection = firestore.collection("entrenamientos");
        DocumentReference document = collection.document(entrenamiento.getEntrenamientoId());
        ApiFuture<QuerySnapshot> querySnapshot = collection.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        for (QueryDocumentSnapshot documento : documents) {
        	Entrenamientos entrenamientoA = documento.toObject(Entrenamientos.class);
        	if (entrenamientoA.getEntrenamientoId().equals(entrenamiento.getEntrenamientoId())) {
        		throw new EntrenamientoConflictException(CONFLICT_STATUS, "La ID de entrenamiento que estás intentando registrar ya se encuentra registrada");
        	}
        }
        persistirEntrenamiento(entrenamiento, document);
        return entrenamientosMapper.toDTO(entrenamiento);
		
	}

	@Override
	public EntrenamientosDTO obtenerEntrenamientos(String id) throws Exception {
		DocumentReference document = firestore.collection("entrenamientos").document(id);
		DocumentSnapshot documentSnapshot = document.get().get();
		if(documentSnapshot.exists()) {
			Entrenamientos entrenamiento = documentSnapshot.toObject(Entrenamientos.class);
			return entrenamientosMapper.toDTO(entrenamiento);			
		}
		else throw new EntrenamientoConflictException(NOT_FOUND_STATUS, "Este entrenamiento no existe");
	}

	@Override
	public void eliminarEntrenamiento(String id) throws Exception {
		DocumentReference document = firestore.collection("entrenamientos").document(id);
		if(encontrarPorId(id) != null) {
			document.delete();
		}
	    else throw new EntrenamientoConflictException(NOT_FOUND_STATUS, "No se encuentra el entrenamiento que estás intentando eliminar");	
	}

	@Override
	public EntrenamientosDTO actualizarEntrenamiento(Entrenamientos entrenamiento) throws Exception {
		DocumentReference document = firestore.collection("entrenamientos").document(entrenamiento.getEntrenamientoId());
		if(encontrarPorId(entrenamiento.getEntrenamientoId()) != null) {
			persistirEntrenamiento(entrenamiento, document);
			return entrenamientosMapper.toDTO(entrenamiento);
		}
		else throw new EntrenamientoConflictException(CONFLICT_STATUS, "Ha habido un error inesperado al intentar actualizar este entrenamiento, por favor, intentalo mas tarde");
	}
	
	public Entrenamientos encontrarPorId(String id) throws Exception {
		Entrenamientos entrenamiento = null;
		CollectionReference collection = firestore.collection("entrenamientos");
		ApiFuture<QuerySnapshot> querySnapshot = collection.get();
		 List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
	        for (QueryDocumentSnapshot documento : documents) {
	        	Entrenamientos entrenamientoA = documento.toObject(Entrenamientos.class);
	        	if (entrenamientoA.getEntrenamientoId().equals(id)) {
	        		entrenamiento = entrenamientoA;
	        		}
	        }
	        if(entrenamiento == null) {
	        	return null;
	        }
	        else return entrenamiento;
	}
	
	public void persistirEntrenamiento(Entrenamientos entrenamiento, DocumentReference document) {
		ObjectMapper mapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
		Map<String, Object> entrenamientoMap = mapper.convertValue(entrenamiento, Map.class);
        document.set(entrenamientoMap);
	}

	@Override
	public List<EntrenamientosDTO> obtenerTodosLosEntrenamientos() throws Exception {
		List<Entrenamientos> entrenamientos = new ArrayList<Entrenamientos>();
		CollectionReference collection = firestore.collection("entrenamientos");
		ApiFuture<QuerySnapshot> querySnapshot = collection.get();
		 List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
		 for (QueryDocumentSnapshot documento : documents) {
	        	Entrenamientos entrenamientoA = documento.toObject(Entrenamientos.class);
	        	entrenamientos.add(entrenamientoA);
	        }
		return entrenamientosMapper.listToDTO(entrenamientos);
	}
}
