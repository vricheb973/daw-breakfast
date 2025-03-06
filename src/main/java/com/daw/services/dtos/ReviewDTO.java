package com.daw.services.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {
	
	private Integer id;
	private Integer idUsuario;
	private Integer idDesayuno;
	private LocalDateTime fecha;
	private Double precio;
	private String imagen;
	private Integer puntuacion;
	private String comentarios;
	
	//Atributos adicionales interesantes para devolver
	private String username;
	private String desayuno;
	private String establecimiento;

}
