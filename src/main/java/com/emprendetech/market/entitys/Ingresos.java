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
@Table(name = "ingresos")
public class Ingresos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="idingresos")
	private Integer idingresos;
	
    @Column(name = "ingreso")
    private Float ingreso;

	@Column(name ="idcaja")
	private Integer idcaja;
	
	@Column(name ="idventa")
	private Integer idventa;
	
	@Column(name = "creadoridusuario")
	private Integer creadoridusuario;
	
	@Column(name="fechacreacion")
	private Timestamp fechacreacion;

	@Column(name="fechamodificacion")
	private Timestamp fechamodificacion;
}
