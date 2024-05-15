package com.emprendetech.market.entitys;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class Roles {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="idrol")
private Integer idrol;

@Column(name="nombre")
private String nombre;

@Column(name="descripcion")
private String descripcion;

@Column(name="creadoridusuario")
private Integer creadoridusuario;

@Column(name="fechacreacion")
private Timestamp fechacreacion;

@Column(name="fechamodificacion")
private Timestamp fechamodificacion;

public Integer getIdrol() {
	return idrol;
}

public void setIdrol(Integer idrol) {
	this.idrol = idrol;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
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
}}