package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Establecimiento;
import com.daw.persistence.repositories.EstablecimientoRepository;

@Service
public class EstablecimientoService {
	
	@Autowired
	private EstablecimientoRepository establecimientoRepository;
	
	//CRUDs
	public List<Establecimiento> findAll(){
		return this.establecimientoRepository.findAll();
	}
	
	public boolean existsEstablecimiento(int idEstablecimiento){
		return this.establecimientoRepository.existsById(idEstablecimiento);
	}
	
	public Optional<Establecimiento> findEntityById(int idEstablecimiento){
		return this.establecimientoRepository.findById(idEstablecimiento);
	}
	
	public Establecimiento create(Establecimiento establecimiento) {
		establecimiento.setPuntuacion(0.0);
		
		return this.establecimientoRepository.save(establecimiento);
	}
	
	public Establecimiento save(Establecimiento establecimiento) {
		Establecimiento establecimientoBD = this.establecimientoRepository.findById(establecimiento.getId()).get();
		
		//Me aseguro de que no se puede modificar la puntuación de un establecimiento.
		establecimiento.setPuntuacion(establecimientoBD.getPuntuacion());
		
		return this.establecimientoRepository.save(establecimiento);
	}
	
	public boolean delete(int idEstablecimiento) {
		boolean result = false;
		
		if(this.establecimientoRepository.existsById(idEstablecimiento)) {
			this.establecimientoRepository.deleteById(idEstablecimiento);
			result = true;
		}
		
		return result;
	}

	//Query methods
	public List<Establecimiento> findByPuntuacion(){
		return this.establecimientoRepository.findAllByOrderByPuntuacionDesc();
	}
	
	public List<Establecimiento> findByUbicacion(String ubicacion){
		return this.establecimientoRepository.findByUbicacionContaining(ubicacion);
	}
}
