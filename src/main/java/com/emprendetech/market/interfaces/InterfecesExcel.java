package com.emprendetech.market.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.service.requestDto.EmprendimientoFechaDto;
import com.emprendetech.market.service.requestDto.FechaDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface InterfecesExcel {


	public ResponseEntity<?> descargarExcel() ;
	

	public ResponseEntity<?> descargarExcelEmprendimiento(@RequestBody EmprendimientoFechaDto datosidfecha) ;
	

	public ResponseEntity<?> descargarExcelventas(@RequestBody FechaDto datosidfecha);
}
