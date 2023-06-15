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
import com.gym.fitlaif.domain.Usuarios;
import com.gym.fitlaif.dto.UsuariosDTO;
import com.gym.fitlaif.exceptions.FitLaifConflictException;
import com.gym.fitlaif.exceptions.FitLaifNotFoundException;
import com.gym.fitlaif.mapper.UsuariosMapper;
import com.gym.fitlaif.service.UsuariosService;

@Service
public class UsuariosServiceImpl implements UsuariosService{
	
	private final int CONFLICT_STATUS = 409;
	private final int NOT_FOUND_STATUS = 404;
    private final Firestore firestore;

    public UsuariosServiceImpl(Firestore firestore) {
        this.firestore = firestore;
    }
    
    @Autowired
    UsuariosMapper usuariosMapper;
    
	@Override
	public UsuariosDTO guardarUsuario(Usuarios usuario) throws Exception {
		CollectionReference collection = firestore.collection("usuarios");
        DocumentReference document = collection.document(usuario.getUsuario());
        ApiFuture<QuerySnapshot> querySnapshot = collection.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        for (QueryDocumentSnapshot documento : documents) {
        	Usuarios usuarioA = documento.toObject(Usuarios.class);
        	if (usuarioA.getUsuario().equals(usuario.getUsuario())) {
        		throw new FitLaifConflictException(CONFLICT_STATUS, "Este usuario ya se encuentra registrado");
        	}
        }
        persistirUsuario(usuario, document);
        return usuariosMapper.toDTO(usuario);
		
	}

	@Override
	public UsuariosDTO obtenerUsuario(String id) throws Exception {
		DocumentReference document = firestore.collection("usuarios").document(id);
		DocumentSnapshot documentSnapshot = document.get().get();
		if(documentSnapshot.exists()) {
			Usuarios usuario = documentSnapshot.toObject(Usuarios.class);
			assert usuario != null;
			return usuariosMapper.toDTO(usuario);
		}
		else throw new FitLaifNotFoundException(NOT_FOUND_STATUS, "Este usuario no existe");
	}

	@Override
	public void eliminarUsuario(String id) throws Exception {
		DocumentReference document = firestore.collection("usuarios").document(id);
		if(encontrarPorId(id) != null) {
			document.delete();
		}
	    else throw new FitLaifNotFoundException(NOT_FOUND_STATUS, "No se encuentra el usuario que estás intentando eliminar");	
	}

	@Override
	public UsuariosDTO actualizarUsuario(Usuarios usuario) throws Exception {
		DocumentReference document = firestore.collection("usuarios").document(usuario.getUsuario());
		if(encontrarPorId(usuario.getUsuario()) != null) {
			persistirUsuario(usuario, document);
			return usuariosMapper.toDTO(usuario);
		}
		else throw new FitLaifConflictException(CONFLICT_STATUS, "Ha habido un error inesperado al intentar actualizar el usuario, por favor, intentalo mas tarde");
	}
	
	public Usuarios encontrarPorId(String id) throws Exception {
		Usuarios usuario = null;
		CollectionReference collection = firestore.collection("usuarios");
		ApiFuture<QuerySnapshot> querySnapshot = collection.get();
		 List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
	        for (QueryDocumentSnapshot documento : documents) {
	        	Usuarios usuarioA = documento.toObject(Usuarios.class);
	        	if (usuarioA.getUsuario().equals(id)) {
	        		usuario = usuarioA;
	        		}
	        }
		return usuario;
	}
	
	public void persistirUsuario(Usuarios usuario, DocumentReference document) {
		ObjectMapper mapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
		Map<String, Object> usuarioMap = mapper.convertValue(usuario, Map.class);
        document.set(usuarioMap);
	}

	@Override
	public List<UsuariosDTO> obtenerTodosLosUsuarios() throws Exception {
		List<Usuarios> usuarios = new ArrayList<>();
		CollectionReference collection = firestore.collection("usuarios");
		ApiFuture<QuerySnapshot> querySnapshot = collection.get();
		 List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
		 for (QueryDocumentSnapshot documento : documents) {
	        	Usuarios usuarioA = documento.toObject(Usuarios.class);
	        	usuarios.add(usuarioA);
	        }
		return usuariosMapper.listToDTO(usuarios);
	}

	@Override
	public UsuariosDTO obtenerUsuarioyContraseña(String id, String password) throws Exception {
		CollectionReference collection = firestore.collection("usuarios");
		DocumentReference document = collection.document(id);
	    Usuarios usuario = encontrarPorId(id);
	    if(usuario == null) {
	    	throw new FitLaifConflictException(CONFLICT_STATUS, "No existe el usuario " );
	    }
	    if (!usuario.getPassword().equals(password) || !usuario.getUsuario().equals(id)) {
	        throw new FitLaifConflictException(CONFLICT_STATUS, "Usuario o contraseña incorrecto(s) ");
	    } else {
	        usuario.setIsLogged(true);
	        persistirUsuario(usuario, document);
	        return usuariosMapper.toDTO(usuario);
	    }
	}

	@Override
	public void cerrarSesion(Usuarios usuario) {
		DocumentReference document = firestore.collection("usuarios").document(usuario.getUsuario());
		usuario.setIsLogged(false);	
		persistirUsuario(usuario, document);
	}
}
