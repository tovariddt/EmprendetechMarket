package com.emprendetech.market.service.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostalRespDto {

	public Integer id_codigo_postal;
	public String clave;
	public String nombre_estado;
	public String nombre_municipio;
	public String nombre_localidad;

}
