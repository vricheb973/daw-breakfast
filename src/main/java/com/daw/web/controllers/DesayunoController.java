package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Desayuno;
import com.daw.services.DesayunoService;
import com.daw.services.EstablecimientoService;
import com.daw.services.dtos.ImagenDTO;


@RestController
@RequestMapping("/desayunos")
public class DesayunoController {

	@Autowired
	private DesayunoService desayunoService;
	
	@Autowired
	private EstablecimientoService establecimientoService;
	
	@GetMapping
	public ResponseEntity<List<Desayuno>> list(){
		return ResponseEntity.ok(this.desayunoService.findAll());
	}
	
	@GetMapping("/{idDesayuno}")
	public ResponseEntity<Desayuno> findById(@PathVariable int idDesayuno) {
		if(!this.desayunoService.existsDesayuno(idDesayuno)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.desayunoService.findEntityById(idDesayuno).get());
	}
	
	@PostMapping
	public ResponseEntity<Desayuno> create(@RequestBody Desayuno desayuno){
		if(!this.establecimientoService.existsEstablecimiento(desayuno.getIdEstablecimiento())){
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<Desayuno>(this.desayunoService.create(desayuno), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idDesayuno}")
	public ResponseEntity<Desayuno> update(@PathVariable int idDesayuno, @RequestBody Desayuno desayuno){
		if(idDesayuno != desayuno.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if(!this.desayunoService.existsDesayuno(idDesayuno)) {
			return ResponseEntity.notFound().build();
		}
		if(!this.establecimientoService.existsEstablecimiento(desayuno.getIdEstablecimiento())){
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.desayunoService.save(desayuno));
	}
	
	@DeleteMapping("/{idDesayuno}")
	public ResponseEntity<Desayuno> delete(@PathVariable int idDesayuno){
		if(this.desayunoService.delete(idDesayuno)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}	
	
	@GetMapping("/puntuacion")
	public ResponseEntity<List<Desayuno>> puntuacion(){
		return ResponseEntity.ok(this.desayunoService.findByPuntuacion());
	}	
	
	@GetMapping("/establecimiento/{idEstablecimiento}/puntuacion")
	public ResponseEntity<List<Desayuno>> puntuacionByEstablecimiento(@PathVariable int idEstablecimiento){
		return ResponseEntity.ok(this.desayunoService.findByEstablecimientoPuntuacion(idEstablecimiento));
	}
	
	@GetMapping("/establecimiento/{idEstablecimiento}/precio")
	public ResponseEntity<List<Desayuno>> precioByEstablecimiento(@PathVariable int idEstablecimiento){
		return ResponseEntity.ok(this.desayunoService.findByEstablecimientoPrecio(idEstablecimiento));
	}
	
	@GetMapping("/establecimiento/{idEstablecimiento}")
	public ResponseEntity<List<Desayuno>> findByEstablecimiento(@PathVariable int idEstablecimiento){
		return ResponseEntity.ok(this.desayunoService.findByEstablecimiento(idEstablecimiento));
	}
	
	@PutMapping("/{idDesayuno}/imagen")
	public ResponseEntity<Desayuno> cambiarImagen(@PathVariable int idDesayuno, @RequestBody ImagenDTO imagenDTO){
		if(!this.desayunoService.existsDesayuno(idDesayuno)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.desayunoService.cambiarImagen(idDesayuno, imagenDTO));
	}
	
}
