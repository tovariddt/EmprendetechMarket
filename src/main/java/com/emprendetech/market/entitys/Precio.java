package com.emprendetech.market.entitys;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

	public Integer getIdprecio() {
		return idprecio;
	}

	public void setIdprecio(Integer idprecio) {
		this.idprecio = idprecio;
	}

	public Integer getIdproductounidad() {
		return idproductounidad;
	}

	public void setIdproductounidad(Integer idproductounidad) {
		this.idproductounidad = idproductounidad;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Integer getCreadoridusuario() {
		return creadoridusuario;
	}

	public void setCreadoridusuario(Integer creadoridusuario) {
		this.creadoridusuario = creadoridusuario;
	}

	public Timestamp getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Timestamp fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public Timestamp getFechamodificacion() {
		return fechamodificacion;
	}

	public void setFechamodificacion(Timestamp fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}
	
	
}
