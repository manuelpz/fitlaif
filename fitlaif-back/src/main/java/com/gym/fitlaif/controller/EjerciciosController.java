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

import com.gym.fitlaif.domain.Ejercicios;
import com.gym.fitlaif.dto.EjerciciosDTO;
import com.gym.fitlaif.service.EjerciciosService;

@CrossOrigin
@RestController
@RequestMapping("/ejercicios")
public class EjerciciosController {

	@Autowired
	EjerciciosService ejerciciosService;
	
	@GetMapping("/{id}")
	private EjerciciosDTO obtenerEjercicios(@PathVariable String id) throws Exception {
		return ejerciciosService.obtenerEjercicios(id);
	}
	
	@GetMapping
	private List<EjerciciosDTO> obtenerTodosLosEjercicios() throws Exception{
		return ejerciciosService.obtenerTodosLosEjercicios();
	}

	@PostMapping("/guardar")
	private EjerciciosDTO guardarEjercicio(@RequestBody Ejercicios ejercicio) throws Exception{
		return ejerciciosService.guardarEjercicio(ejercicio);
	}
	
	@DeleteMapping("/eliminar/{id}")
	private void borrarEjericicio(@PathVariable String id) throws Exception {
		ejerciciosService.eliminarEjercicio(id);
	}
	
	@PutMapping("/actualizar")
	private EjerciciosDTO actualizarEjercicio(@RequestBody Ejercicios ejercicio) throws Exception {
		return ejerciciosService.actualizarEjercicio(ejercicio);
	}
}
