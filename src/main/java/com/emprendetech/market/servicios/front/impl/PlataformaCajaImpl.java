package com.emprendetech.market.servicios.front.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emprendetech.market.controllers.CajaController;
import com.emprendetech.market.controllers.IngresosController;
import com.emprendetech.market.entitys.Caja;
import com.emprendetech.market.entitys.Ingresos;
import com.emprendetech.market.interfaces.InterfaceCaja;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.CajaDto;
import com.emprendetech.market.service.requestDto.IngresosDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ResponseBody
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)

@RestController
@RequestMapping(path = "/api/cajasyingresos")
public class PlataformaCajaImpl implements InterfaceCaja {
	
	
	private static final Log LOG = LogFactory.getLog(PlataformaCajaImpl.class);
	
	@Autowired
	private CajaController AltaCaja, ActualizarCaja;
	
	@Autowired
	private IngresosController AltaIngresos, ActualizarIngresos;
	
	@Override
	@PostMapping("/alta/caja")
	@Operation(summary = "Se realiza un registro de caja")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de caja")
	public ResponseEntity<?> postCaja(@RequestBody CajaDto cajaDto) {

		LOG.info("get Caja - get Caja() Method");
		ResponseEntity<?> responsealtaCaja = null;

		try {

			String altaCaja = null;

			altaCaja = AltaCaja.AltaCaja(cajaDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta Caja");
			responseContenido.setContenido(altaCaja);
			responsealtaCaja = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Caja");
			responsealtaCaja = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaCaja;
	}
	
	

	@Override
	@PostMapping("/actualizar/caja")
	@Operation(summary = "Se realiza una actualizacion de caja")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de caja")
	public ResponseEntity<?> updateCaja(@RequestBody Caja caja) {

		LOG.info("update Caja - update Caja() Method");
	
		ResponseEntity<?> responseactualizarCaja = null;

		try {

			String actualizarCaja = null;

			actualizarCaja = ActualizarCaja.actualizarCaja(caja);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Caja");
			responseContenido.setContenido(actualizarCaja);
			responseactualizarCaja = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Caja");
			responseactualizarCaja = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarCaja;
	}



	@Override
	@PostMapping("/alta/ingresos")
	@Operation(summary = "Se realiza un registro de ingresos")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza un registro de ingresos")
	public ResponseEntity<?> postIngresos(IngresosDto ingresosDto) {
		LOG.info("get Ingresos - get Ingresos() Method");
		ResponseEntity<?> responsealtaIngresos = null;

		try {

			String altaIngresos = null;

			altaIngresos = AltaIngresos.AltaIngresos(ingresosDto);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Alta  Ingresos");
			responseContenido.setContenido(altaIngresos);
			responsealtaIngresos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("eror codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Alta Ingresos");
			responsealtaIngresos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responsealtaIngresos;
	}



	@Override
	@PostMapping("/actualizar/ingresos")
	@Operation(summary = "Se realiza una actualizacion de ingresos")
    @ApiResponse(responseCode = "200", description = "Con este metodo se realiza una actualizacion de ingresos")
	public ResponseEntity<?> updateIngresos(Ingresos ingresos) {
		LOG.info("update Caja - update Caja() Method");
		
		ResponseEntity<?> responseactualizarIngresos = null;

		try {

			String actualizarIngresos = null;

			actualizarIngresos = ActualizarIngresos.actualizarIngresos(ingresos);
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("200 OK", "Actualizar Ingresos");
			responseContenido.setContenido(actualizarIngresos);
			responseactualizarIngresos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		} catch (Exception e) {
			LOG.error("error codigo" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", "Actualizar Ingresos");
			responseactualizarIngresos = new ResponseEntity<>(responseContenido, HttpStatus.OK);

		}
		return responseactualizarIngresos;
	}





}
