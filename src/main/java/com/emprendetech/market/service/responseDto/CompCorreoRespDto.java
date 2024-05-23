package com.emprendetech.market.service.responseDto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
public class CompCorreoRespDto {

	public String Mensaje;
	public String Correo;
	public boolean Valor;
}