package com.daw.services.mappers;

import java.util.ArrayList;
import java.util.List;

import com.daw.persistence.entities.Review;
import com.daw.services.dtos.ReviewDTO;

public class ReviewMapper {
	
	public static ReviewDTO toDto(Review review) {
		ReviewDTO dto = new ReviewDTO();
		
		//Vamos recogiendo los atributos de ReviewDTO de las distintas entidades
		//Vienen de Review
		dto.setId(review.getId());
		dto.setIdUsuario(review.getIdUsuario());
		dto.setIdDesayuno(review.getIdDesayuno());
		dto.setFecha(review.getFecha());
		dto.setPrecio(review.getPrecio());
		dto.setImagen(review.getImagen());
		dto.setPuntuacion(review.getPuntuacion());
		dto.setComentarios(review.getComentarios());
		
		//Vienen del Usuario asociado a la Review
		dto.setUsername(review.getUsuario().getUsername());
		
		//Vienen del Desayuno asociado a la Review
		dto.setDesayuno(review.getDesayuno().getNombre());
		dto.setEstablecimiento(review.getDesayuno().getEstablecimiento().getNombre());
		
		return dto;
	}
	
	public static List<ReviewDTO> toDtos(List<Review> reviews){
		List<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();
		
		for(Review r : reviews) {
			reviewDTOs.add(ReviewMapper.toDto(r));
		}
		
		return reviewDTOs;
	}

}
