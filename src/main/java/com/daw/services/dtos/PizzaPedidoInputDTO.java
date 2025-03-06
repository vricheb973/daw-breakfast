package com.daw.services.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PizzaPedidoInputDTO {
	
	private Integer id;
	private Integer idPedido;
	private Integer idPizza;
	private Double cantidad;

}
