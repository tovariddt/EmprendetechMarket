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
@Table(name = "categorias")
public class Categorias {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idcategoria")
	private Integer idcategoria;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;

	@Column(name = "fechacreacion")
	private Timestamp fechacreacion;

	@Column(name = "fechamodificacion")
	private Timestamp fechamodificacion;

}
