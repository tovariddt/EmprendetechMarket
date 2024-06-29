package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Caja;
import com.emprendetech.market.repositorys.CajaRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.CajaDto;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;


@Controller
public class CajaController {

	private static final Log LOG = LogFactory.getLog(CajaController.class);

	@Autowired
	private CajaRepository cajaRepository;

	public String AltaCaja(@RequestBody CajaDto cajaDto) throws Exception {
		LOG.info("createAlta Caja - createAlta Caja() Method");

		String response = null;
		try {
			Caja CajaInsert = new Caja();

			CajaInsert.setNombrecaja(cajaDto.getNombrecaja());
			CajaInsert.setCreadoridusuario(cajaDto.getCreadoridusuario());

            
			Utils util = new Utils();
			CajaInsert.setFechacreacion(util.currentDate());
			CajaInsert.setFechamodificacion(util.currentDate());
			LOG.info("Alta Caja - Alta Caja () Method " + CajaInsert.toString());

			CajaInsert = cajaRepository.save(CajaInsert);

			response = Constantes.FELICIDADESALTACAJAS+ CajaInsert.getNombrecaja();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", cajaDto.toString());
		}
		return response;
	}

	public String actualizarCaja(@RequestBody Caja caja) throws Exception {
		LOG.info("update caja - update caja() Method");

		String response = null;

		try {
			Caja cajaExistente = cajaRepository.findById(caja.getIdcaja()).orElseThrow();

			cajaExistente.setNombrecaja(caja.getNombrecaja());

			Utils util = new Utils();
			cajaExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Caja - update Caja() Method " + cajaExistente.toString());

			cajaExistente = cajaRepository.save(cajaExistente);

			response =  Constantes.FELICIDADESACTUALIZARPRODUCTO+ cajaExistente.getNombrecaja() ;
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", caja.toString());
		}
		return response;
	}
	}
