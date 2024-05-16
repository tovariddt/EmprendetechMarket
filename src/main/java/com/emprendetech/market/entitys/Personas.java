package com.emprendetech.market.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;

import javax.management.loading.PrivateClassLoader;

@Entity
@Table(name="personas")
public class Personas {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="idpersona")
private Integer idpersona;

@Column(name="nombre" )
private String  nombre;

@Column(name = "apellido_materno")
private String apellido_materno;

@Column(name = "apellido_paterno")
private String apellido_paterno;

@Column(name = "direccion")
private String direccion;

@Column(name = "telefono")
private String telefono; 

@Column(name = "referencia")
private String refencia;

@Column(name = "creadoridusuario")
private Integer creadoridusuario;

@Column(name="fechacreacion")
private Timestamp fechacreacion;

@Column(name="fechamodificacion")
private Timestamp fechamodificacion;

@Column(name = "id_codigo_postal")
private Integer id_codigo_postal;


public Integer getIdpersona() {
	return idpersona;
}

public void setIdpersona(Integer idpersona) {
	this.idpersona = idpersona;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellido_materno() {
	return apellido_materno;
}

public void setApellido_materno(String apellido_materno) {
	this.apellido_materno = apellido_materno;
}

public String getApellido_paterno() {
	return apellido_paterno;
}

public void setApellido_paterno(String apellido_paterno) {
	this.apellido_paterno = apellido_paterno;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public String getRefencia() {
	return refencia;
}

public void setRefencia(String refencia) {
	this.refencia = refencia;
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

public Integer getId_codigo_postal() {
	return id_codigo_postal;
}

public void setId_codigo_postal(Integer id_codigo_postal) {
	this.id_codigo_postal = id_codigo_postal;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}


}
