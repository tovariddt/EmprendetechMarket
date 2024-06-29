package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Caja;
import com.emprendetech.market.entitys.Ingresos;
import com.emprendetech.market.repositorys.CajaRepository;
import com.emprendetech.market.repositorys.IngresosRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.CajaDto;
import com.emprendetech.market.service.requestDto.IngresosDto;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;

@Controller
public class IngresosController {

	private static final Log LOG = LogFactory.getLog(IngresosController.class);

	@Autowired
	private IngresosRepository ingresosRepository;

	public String AltaIngresos(@RequestBody IngresosDto ingresosDto) throws Exception {
		LOG.info("createAlta Ingresos - createAlta Ingresos() Method");

		String response = null;
		try {
			Ingresos IngresosInsert = new Ingresos();

			IngresosInsert.setIdventa(ingresosDto.getIdventa());
			IngresosInsert.setIdcaja(ingresosDto.getIdcaja());
			IngresosInsert.setIngreso(ingresosDto.getIngreso());
			IngresosInsert.setCreadoridusuario(ingresosDto.getCreadoridusuario());

            
			Utils util = new Utils();
			IngresosInsert.setFechacreacion(util.currentDate());
			IngresosInsert.setFechamodificacion(util.currentDate());
			LOG.info("Alta Ingresos - Alta Ingresos() Method " +  IngresosInsert.toString());

			IngresosInsert = ingresosRepository.save(IngresosInsert);

			response = Constantes.FELICIDADESALTAINGRESOS+ IngresosInsert.getIdingresos();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", ingresosDto.toString());
		}
		return response;
	}

	public String actualizarIngresos(@RequestBody Ingresos ingresos) throws Exception {
		LOG.info("update Ingresos - update Ingresos() Method");

		String response = null;

		try {
			Ingresos ingresosExistente = ingresosRepository.findById(ingresos.getIdingresos()).orElseThrow();
 
			ingresosExistente.setIdventa(ingresos.getIdventa());
			ingresosExistente.setIdcaja(ingresos.getIdcaja());
			ingresosExistente.setIngreso(ingresos.getIngreso());

			Utils util = new Utils();
			ingresosExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Ingresos - update Ingresos() Method " + ingresosExistente.toString());

			ingresosExistente = ingresosRepository.save(ingresosExistente);

			response =  Constantes.FELICIDADESACTUALIZARINGRESOS+ ingresosExistente.getIdingresos() ;
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", ingresos.toString());
		}
		return response;
	}
	}

