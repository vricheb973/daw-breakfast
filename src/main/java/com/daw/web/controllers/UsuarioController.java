package com.daw.web.controllers;

import java.util.List;
import java.util.Optional;

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

import com.daw.persistence.entities.Usuario;
import com.daw.services.UsuarioService;
import com.daw.services.dtos.PasswordDTO;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> list(){
		return ResponseEntity.ok(this.usuarioService.findAll());
	}
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<Usuario> findById(@PathVariable int idUsuario) {
		if(!this.usuarioService.existsUsuario(idUsuario)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.usuarioService.findEntityById(idUsuario).get());
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
		return new ResponseEntity<Usuario>(this.usuarioService.create(usuario), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idUsuario}")
	public ResponseEntity<Usuario> update(@PathVariable int idUsuario, @RequestBody Usuario usuario){
		if(idUsuario != usuario.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if(!this.usuarioService.existsUsuario(idUsuario)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.usuarioService.save(usuario));
	}
	
	@DeleteMapping("/{idUsuario}")
	public ResponseEntity<Usuario> delete(@PathVariable int idUsuario){
		if(this.usuarioService.delete(idUsuario)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}	
	
	@PutMapping("/{idUsuario}/password")
	//Pongo ResponseEntity<Object> para poder devolver texto en el body del badRequest
	public ResponseEntity<Object> cambiarPassword(@PathVariable int idUsuario, @RequestBody PasswordDTO passwordDTO){
		Optional<Usuario> usuarioOpt = this.usuarioService.findEntityById(idUsuario);
		
		if(usuarioOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		if(!usuarioOpt.get().getPassword().equals(passwordDTO.getVieja())) {
			return ResponseEntity.badRequest().body("La contraseña antigua no coincide. ");			
		}
		
		return ResponseEntity.ok(this.usuarioService.cambiarPassword(idUsuario, passwordDTO));
	}
	
}
