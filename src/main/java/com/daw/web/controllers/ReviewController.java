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

import com.daw.persistence.entities.Review;
import com.daw.services.DesayunoService;
import com.daw.services.ReviewService;
import com.daw.services.UsuarioService;
import com.daw.services.dtos.ReviewDTO;


@RestController
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired 
	private UsuarioService usuarioService;
	
	@Autowired
	private DesayunoService desayunoService;
	
	@GetMapping
	public ResponseEntity<List<ReviewDTO>> list(){
		return ResponseEntity.ok(this.reviewService.findAll());
	}
	
	@GetMapping("/{idReview}")
	public ResponseEntity<ReviewDTO> findById(@PathVariable int idReview) {
		if(!this.reviewService.existsReview(idReview)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.reviewService.findDTOById(idReview));
	}
	
	@PostMapping
	public ResponseEntity<ReviewDTO> create(@RequestBody Review review){
		if(!this.usuarioService.existsUsuario(review.getIdUsuario())) {
			return ResponseEntity.notFound().build();
		}
		if(!this.desayunoService.existsDesayuno(review.getIdDesayuno())) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<ReviewDTO>(this.reviewService.create(review), HttpStatus.CREATED);
	}
	
	@PutMapping("/{idReview}")
	public ResponseEntity<ReviewDTO> update(@PathVariable int idReview, @RequestBody Review review){
		if(idReview != review.getId()) {
			return ResponseEntity.badRequest().build();
		}
		if(!this.reviewService.existsReview(idReview)) {
			return ResponseEntity.notFound().build();
		}
		if(!this.usuarioService.existsUsuario(review.getIdUsuario())) {
			return ResponseEntity.notFound().build();
		}
		if(!this.desayunoService.existsDesayuno(review.getIdDesayuno())) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.reviewService.save(review));
	}
	
	@DeleteMapping("/{idReview}")
	public ResponseEntity<ReviewDTO> delete(@PathVariable int idReview){
		if(this.reviewService.delete(idReview)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}	
	
	@GetMapping("/recientes")
	public ResponseEntity<List<ReviewDTO>> recientes(){
		return ResponseEntity.ok(this.reviewService.findRecientes());
	}
	
	@GetMapping("/antiguas")
	public ResponseEntity<List<ReviewDTO>> antiguas(){
		return ResponseEntity.ok(this.reviewService.findAntiguas());
	}
	
	@GetMapping("/puntuacion")
	public ResponseEntity<List<ReviewDTO>> puntuacion(){
		return ResponseEntity.ok(this.reviewService.findByPuntuacion());
	}
	
	@GetMapping("/desayuno/{idDesayuno}/fecha")
	public ResponseEntity<List<ReviewDTO>> findByDesayunoFecha(@PathVariable int idDesayuno){
		return ResponseEntity.ok(this.reviewService.findByDesayunoFecha(idDesayuno));
	}
	
	@GetMapping("/desayuno/{idDesayuno}/puntuacion")
	public ResponseEntity<List<ReviewDTO>> findByDesayunoPuntuacion(@PathVariable int idDesayuno){
		return ResponseEntity.ok(this.reviewService.findByDesayunoPuntuacion(idDesayuno));
	}
	
}
