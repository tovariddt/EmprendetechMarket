package com.emprendetech.market.entitys;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter 
@Setter 
@NoArgsConstructor 
@Entity
@Table(name = "precio")
public class Precio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idprecio")
	private Integer idprecio;
	
	@Column(name = "idproductounidad")
	private Integer idproductounidad;
	
	@Column(name = "precio")
	private Float precio;
	
	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;
	
	@Column(name="fechacreacion")
	private Timestamp fechacreacion;

	@Column(name="fechamodificacion")
	private Timestamp fechamodificacion;

	
	
}
