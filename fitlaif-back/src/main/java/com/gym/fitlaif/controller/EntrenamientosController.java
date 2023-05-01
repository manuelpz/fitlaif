package com.gym.fitlaif.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.fitlaif.domain.Entrenamientos;
import com.gym.fitlaif.dto.EntrenamientosDTO;
import com.gym.fitlaif.service.EntrenamientosService;

@CrossOrigin
@RestController
@RequestMapping("/entrenamientos")
public class EntrenamientosController {
	
	@Autowired
	EntrenamientosService entrenamientosService;
	
	@GetMapping("/{id}")
	private EntrenamientosDTO obtenerEntrenamientos(@PathVariable String id) throws Exception {
		return entrenamientosService.obtenerEntrenamientos(id);
	}
	
	@GetMapping
	private List<EntrenamientosDTO> obtenerTodosLosEntrenamientos() throws Exception{
		return entrenamientosService.obtenerTodosLosEntrenamientos();
	}

	@PostMapping("/guardar")
	private EntrenamientosDTO guardarEntrenamiento(@RequestBody Entrenamientos entrenamiento) throws Exception{
		return entrenamientosService.guardarEntrenamiento(entrenamiento);
	}
	
	@DeleteMapping("/eliminar/{id}")
	private void borrarEntrenamiento(@PathVariable String id) throws Exception {
		entrenamientosService.eliminarEntrenamiento(id);
	}
	
	@PutMapping("/actualizar")
	private EntrenamientosDTO actualizarEntrenamiento(@RequestBody Entrenamientos entrenamiento) throws Exception {
		return entrenamientosService.actualizarEntrenamiento(entrenamiento);
	}
}
