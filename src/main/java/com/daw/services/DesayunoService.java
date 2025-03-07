package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Desayuno;
import com.daw.persistence.repositories.DesayunoRepository;
import com.daw.services.dtos.ImagenDTO;

@Service
public class DesayunoService {
	
	private final String IMAGEN_POR_DEFECTO = "https://i.pinimg.com/736x/6d/7a/43/6d7a43e03c4a75a218a47bb6fd5bfcd0.jpg";
	
	@Autowired
	private DesayunoRepository desayunoRepository;
	
	//CRUDs
	public List<Desayuno> findAll(){
		return this.desayunoRepository.findAll();
	}
	
	public boolean existsDesayuno(int idDesayuno){
		return this.desayunoRepository.existsById(idDesayuno);
	}
	
	public Optional<Desayuno> findEntityById(int idDesayuno){
		return this.desayunoRepository.findById(idDesayuno);
	}
	
	public Desayuno create(Desayuno desayuno) {
		desayuno.setPuntuacion(0.0);
		
		return this.desayunoRepository.save(desayuno);
	}
	
	public Desayuno save(Desayuno desayuno) {
		Desayuno desayunoBD = this.desayunoRepository.findById(desayuno.getId()).get();
		
		//Me aseguro de que no se puede modificar la puntuación de un desayuno.
		desayuno.setPuntuacion(desayunoBD.getPuntuacion());
				
		return this.desayunoRepository.save(desayuno);
	}
	
	public boolean delete(int idDesayuno) {
		boolean result = false;
		
		if(this.desayunoRepository.existsById(idDesayuno)) {
			this.desayunoRepository.deleteById(idDesayuno);
			result = true;
		}
		
		return result;
	}
	
	//Query methods
	public List<Desayuno> findByPuntuacion(){
		return this.desayunoRepository.findAllByOrderByPuntuacionDesc();
	}

	public List<Desayuno> findByEstablecimientoPuntuacion(int idEstablecimiento){
		return this.desayunoRepository.findByIdEstablecimientoOrderByPuntuacionDesc(idEstablecimiento);
	}

	public List<Desayuno> findByEstablecimientoPrecio(int idEstablecimiento){
		return this.desayunoRepository.findByIdEstablecimientoOrderByPrecioAsc(idEstablecimiento);
	}

	public List<Desayuno> findByEstablecimiento(int idEstablecimiento){
		return this.desayunoRepository.findByIdEstablecimiento(idEstablecimiento);
	}
	
	public Desayuno cambiarImagen(int idDesayuno, ImagenDTO imagenDTO) {
		Desayuno desayuno = this.desayunoRepository.findById(idDesayuno).get();
		
		if(imagenDTO.getImagen() == null || imagenDTO.getImagen().isBlank()) {
			desayuno.setImagen(IMAGEN_POR_DEFECTO);
		}
		else {
			desayuno.setImagen(imagenDTO.getImagen());
		}
		
		
		return this.desayunoRepository.save(desayuno);
	}
	
}
