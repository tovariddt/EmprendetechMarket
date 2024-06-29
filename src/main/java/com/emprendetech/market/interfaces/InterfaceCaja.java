package com.emprendetech.market.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Caja;
import com.emprendetech.market.entitys.Ingresos;
import com.emprendetech.market.service.requestDto.CajaDto;
import com.emprendetech.market.service.requestDto.IngresosDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface InterfaceCaja {
	

	public abstract ResponseEntity<?> postCaja(@RequestBody CajaDto cajaDto);
	

	public ResponseEntity<?> updateCaja(@RequestBody Caja caja);
	

	public ResponseEntity<?> postIngresos(@RequestBody IngresosDto ingresosDto);
	

	public ResponseEntity<?> updateIngresos(@RequestBody Ingresos ingresos);
	

}
