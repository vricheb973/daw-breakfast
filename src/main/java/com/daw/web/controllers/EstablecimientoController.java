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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Establecimiento;
import com.daw.services.EstablecimientoService;


@RestController
@RequestMapping("/establecimientos")
public class EstablecimientoController {

	@Autowired
	private EstablecimientoService establecimientoService;
	
	@GetMapping
	public ResponseEntity<List<Establecimiento>> list(){
		return ResponseEntity.ok(this.establecimientoService.findAll());
	}
	
	@GetMapping("/{idEstablecimiento}")
	public ResponseEntity<Establecimiento> findById(@PathVariable int idEstablecimiento) {
		if(!this.establecimientoService.existsEstablecimiento(idEstablecimiento)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.establecimientoService.findEntityById(idEstablecimiento).get());
	}
	
	@PostMapping
	public ResponseEntity<Establecimiento> create(@RequestBody Establecimiento establecimiento){
		return new ResponseEntity<Establecimiento>(this.establecimientoService.create(establecimiento), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idEstablecimiento}")
	public ResponseEntity<Establecimiento> update(@PathVariable int idEstablecimiento, @RequestBody Establecimiento establecimiento){
		if(idEstablecimiento != establecimiento.getId()) {
			return ResponseEntity.badRequest().build();
		}
		else if(!this.establecimientoService.existsEstablecimiento(idEstablecimiento)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.establecimientoService.save(establecimiento));
	}
	
	@DeleteMapping("/{idEstablecimiento}")
	public ResponseEntity<Establecimiento> delete(@PathVariable int idEstablecimiento){
		if(this.establecimientoService.delete(idEstablecimiento)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}	
	
	@GetMapping("/puntuacion")
	public ResponseEntity<List<Establecimiento>> puntuacion(){
		return ResponseEntity.ok(this.establecimientoService.findByPuntuacion());
	}
	
	@GetMapping("/ubicación")
	public ResponseEntity<List<Establecimiento>> buscarUbicacion(@RequestParam String ubicacion){
		return ResponseEntity.ok(this.establecimientoService.findByUbicacion(ubicacion));
	}
	
}
