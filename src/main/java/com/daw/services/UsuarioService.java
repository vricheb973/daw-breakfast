package com.daw.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Usuario;
import com.daw.persistence.repositories.UsuarioRepository;
import com.daw.services.dtos.PasswordDTO;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//CRUDs
	public List<Usuario> findAll(){
		return this.usuarioRepository.findAll();
	}
	
	public boolean existsUsuario(int idUsuario){
		return this.usuarioRepository.existsById(idUsuario);
	}
	
	public Optional<Usuario> findEntityById(int idUsuario){
		return this.usuarioRepository.findById(idUsuario);
	}
	
	public Usuario create(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
	public Usuario save(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}
	
	public boolean delete(int idUsuario) {
		boolean result = false;
		
		if(this.usuarioRepository.existsById(idUsuario)) {
			this.usuarioRepository.deleteById(idUsuario);
			result = true;
		}
		
		return result;
	}
	
	public Usuario cambiarPassword(int idUsuario, PasswordDTO passwordDTO) {
		Usuario usuario = this.usuarioRepository.findById(idUsuario).get();

		usuario.setPassword(passwordDTO.getNueva());
		
		return this.usuarioRepository.save(usuario);
	}

}
