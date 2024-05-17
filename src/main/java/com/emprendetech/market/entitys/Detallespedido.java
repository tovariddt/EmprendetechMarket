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
@Table(name = "detallespedido")
public class Detallespedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="iddetallespedido")
	private Integer iddetallespedido;
	
	@Column(name = "idventa")
	private Integer idventa;
	
	@Column(name = "idproductounidad")
	private Integer idproductounidad;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "subtotal")
	private Float subtotal;
	
	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;
	
	@Column(name="fechacreacion")
	private Timestamp fechacreacion;

	@Column(name="fechamodificacion")
	private Timestamp fechamodificacion;

}
