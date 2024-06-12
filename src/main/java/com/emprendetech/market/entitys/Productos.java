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
@Table(name = "productos")
public class Productos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idproducto" )
	private Integer idproducto;
	
	@Column(name = "sku")
	private String sku;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "imagen")
	private String  imagen;
	
	@Column(name = "idemprendimiento")
	private Integer idemprendimiento;
	
	@Column(name = "idcategoria")
	private Integer idcategoria;
	
	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;
	
	@Column(name="fechacreacion")
	private Timestamp fechacreacion;

	@Column(name="fechamodificacion")
	private Timestamp fechamodificacion;


	

}
