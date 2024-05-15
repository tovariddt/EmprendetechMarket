package com.emprendetech.market.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


				//	@Setter
					//@Getter
					//@AllArgsConstructor
				//	@NoArgsConstructor
@Entity
@Table(name="perfiles")
public class Perfiles {

	
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="idperfil")
private Integer idperfil;

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

public Integer getIdperfil() {
	return idperfil;
}

public void setIdperfil(Integer idperfil) {
	this.idperfil = idperfil;
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
}



}
