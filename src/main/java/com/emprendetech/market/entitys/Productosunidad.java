package com.emprendetech.market.entitys;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productosunidad")
public class Productosunidad {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idproductounidad")
	private Integer idproductounidad;
	
	@Column(name = "idproducto")
	private Integer idproducto;
	
	@Column(name = "idmedida")
	private Integer idmedida;
	
	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;
	
	@Column(name="fechacreacion")
	private Timestamp fechacreacion;

	@Column(name="fechamodificacion")
	private Timestamp fechamodificacion;

	public Integer getIdproductounidad() {
		return idproductounidad;
	}

	public void setIdproductounidad(Integer idproductounidad) {
		this.idproductounidad = idproductounidad;
	}

	public Integer getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public Integer getIdmedida() {
		return idmedida;
	}

	public void setIdmedida(Integer idmedida) {
		this.idmedida = idmedida;
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
