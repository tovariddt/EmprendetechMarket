package com.emprendetech.market.entitys;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

	public Integer getIddetallespedido() {
		return iddetallespedido;
	}

	public void setIddetallespedido(Integer iddetallespedido) {
		this.iddetallespedido = iddetallespedido;
	}

	public Integer getIdventa() {
		return idventa;
	}

	public void setIdventa(Integer idventa) {
		this.idventa = idventa;
	}

	public Integer getIdproductounidad() {
		return idproductounidad;
	}

	public void setIdproductounidad(Integer idproductounidad) {
		this.idproductounidad = idproductounidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
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
