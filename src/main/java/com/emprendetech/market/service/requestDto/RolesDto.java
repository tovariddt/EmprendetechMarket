package com.emprendetech.market.service.requestDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 

public class RolesDto {

private String nombre;


private String descripcion;


private Integer creadoridusuario;

private Timestamp fechacreacion;

private Timestamp fechamodificacion;

}
