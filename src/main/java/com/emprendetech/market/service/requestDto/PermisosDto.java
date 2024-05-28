package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class PermisosDto {
	 
		
		@Column(name = "nombre")
		private String nombre;
		
		@Column(name = "descripcion")
		private String descripcion;
		
		@Column(name = "creadoridusuario")
		private Integer creadoridusuario;
		
		@Column(name="fechacreacion")
		private Timestamp fechacreacion;

		@Column(name="fechamodificacion")
		private Timestamp fechamodificacion;


		
}
