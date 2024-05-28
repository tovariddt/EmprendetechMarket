package com.emprendetech.market.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Getter 
@Setter 
@NoArgsConstructor 
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
private String referencia;

@Column(name = "creadoridusuario")
private Integer creadoridusuario;

@Column(name="fechacreacion")
private Timestamp fechacreacion;

@Column(name="fechamodificacion")
private Timestamp fechamodificacion;

@Column(name = "id_codigo_postal")
private Integer id_codigo_postal;


}
