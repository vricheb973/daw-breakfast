package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.entities.Establecimiento;
import com.daw.persistence.entities.Review;
import com.daw.persistence.repositories.ReviewRepository;
import com.daw.services.dtos.ReviewDTO;
import com.daw.services.mappers.ReviewMapper;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired 
	private DesayunoService desayunoService;
	
	@Autowired
	private EstablecimientoService establecimientoService;
	
	//CRUDs
	public List<ReviewDTO> findAll(){
		return ReviewMapper.toDtos(this.reviewRepository.findAll());
	}
	
	public boolean existsReview(int idReview){
		return this.reviewRepository.existsById(idReview);
	}
	
	public ReviewDTO findDTOById(int idReview){
		return ReviewMapper.toDto(this.reviewRepository.findById(idReview).get());
	}
	
	public Optional<Review> findEntityById(int idReview){
		return this.reviewRepository.findById(idReview);
	}
	
	public ReviewDTO create(Review review) {
		review = this.reviewRepository.save(review);
		
		int idEstablecimiento = this.actualizarPuntuacionDesayuno(review.getIdDesayuno());
		
		this.actualizarPuntuacionEstablecimiento(idEstablecimiento);
		
		return ReviewMapper.toDto(review);
	}
	
	public ReviewDTO save(Review review) {
		review = this.reviewRepository.save(review);
		
		int idEstablecimiento = this.actualizarPuntuacionDesayuno(review.getIdDesayuno());
		
		this.actualizarPuntuacionEstablecimiento(idEstablecimiento);
		
		return ReviewMapper.toDto(review);
	}
	
	public boolean delete(int idReview) {
		boolean result = false;
		
		if(this.reviewRepository.existsById(idReview)) {
			this.reviewRepository.deleteById(idReview);
			
			//Recupero la review para obtener el ID del desayuno y poder actualizar puntuaciones
			Review review = this.reviewRepository.findById(idReview).get();
			int idEstablecimiento = this.actualizarPuntuacionDesayuno(review.getIdDesayuno());
			this.actualizarPuntuacionEstablecimiento(idEstablecimiento);
			
			result = true;
		}
		
		return result;
	}

	public List<ReviewDTO> findRecientes(){
		return ReviewMapper.toDtos(this.reviewRepository.findAllByOrderByFechaDesc());
	}
	
	public List<ReviewDTO> findAntiguas(){
		return ReviewMapper.toDtos(this.reviewRepository.findAllByOrderByFechaAsc());
	}
	
	public List<ReviewDTO> findByPuntuacion(){
		return ReviewMapper.toDtos(this.reviewRepository.findAllByOrderByPuntuacionDesc());
	}
	
	public List<ReviewDTO> findByDesayunoFecha(int idDesayuno){
		return ReviewMapper.toDtos(this.reviewRepository.findByIdDesayunoOrderByFechaDesc(idDesayuno));
	}
	
	public List<ReviewDTO> findByDesayunoPuntuacion(int idDesayuno){
		return ReviewMapper.toDtos(this.reviewRepository.findByIdDesayunoOrderByPuntuacionDesc(idDesayuno));
	}
	
	private int actualizarPuntuacionDesayuno(int idDesayuno) {
		Desayuno desayuno = this.desayunoService.findEntityById(idDesayuno).get();
		
		double total = 0.0;
		
		for(Review r : desayuno.getReviews()) {
			total = r.getPuntuacion();
		}
		
		total = total / desayuno.getReviews().size();
		
		desayuno.setPuntuacion(total);
		
		this.desayunoService.save(desayuno);
		
		//Devuelvo el ID del establecimiento para no tener que consultarlo en el create ni en el update
		return desayuno.getIdEstablecimiento();
	}
	
	private void actualizarPuntuacionEstablecimiento(int idEstablecimiento) {
		Establecimiento establecimiento = this.establecimientoService.findEntityById(idEstablecimiento).get();
		
		double total = 0.0;
		
		for(Desayuno d : establecimiento.getDesayunos()) {
			total = d.getPuntuacion();
		}
		
		total = total / establecimiento.getDesayunos().size();
		
		establecimiento.setPuntuacion(total);
		
		this.establecimientoService.save(establecimiento);
	}
	
}
