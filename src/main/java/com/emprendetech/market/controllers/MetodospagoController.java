package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Metodospago;
import com.emprendetech.market.repositorys.MetodospagoRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.MetodospagoDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;
@Data
@Controller
public class MetodospagoController {
	private static final Log LOG = LogFactory.getLog(MetodospagoController.class);

	@Autowired
	private  MetodospagoRepository metodospagoRepository;

	public String AltaMetodospago(@RequestBody MetodospagoDto metodospagoDto) throws Exception {
		LOG.info("createAlta Metodospago- createAlta Metodospago() Method");
		LOG.debug("createAlta Metodospago:: " + metodospagoDto.toString());

		String response = null;
		try {
			Metodospago MetodospagoInsert = new Metodospago();

			
			MetodospagoInsert.setNombre(metodospagoDto.getNombre());
			MetodospagoInsert.setCreadoridusuario(metodospagoDto.getCreadoridusuario());
			MetodospagoInsert.setEstatus(metodospagoDto.getEstatus());
			Utils util = new Utils();
			MetodospagoInsert.setFechacreacion(util.currentDate());
			MetodospagoInsert.setFechamodificacion(util.currentDate());
			
			LOG.info("Alta Metodospago - Alta Metodospago() Method " + MetodospagoInsert.toString());

			MetodospagoInsert = metodospagoRepository.save(MetodospagoInsert);

			response = "Felicidades Su Metodo de pago fue registrado como = " + MetodospagoInsert.getNombre();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", metodospagoDto.toString());
		}
		return response;
	}
	
	public String actualizarMetodospago(@RequestBody Metodospago metodospago) throws Exception {
		LOG.info("update Metodospago - update Metodospago() Method");
		LOG.debug("update Metodospago :: " + metodospago.toString());

		String response = null;

		try {
			Metodospago metodospagoExistente = metodospagoRepository.findById(metodospago.getIdmetodospago()).orElseThrow();

			metodospagoExistente.setNombre(metodospago.getNombre());
			metodospagoExistente.setCreadoridusuario(metodospago.getCreadoridusuario());
			metodospagoExistente.setEstatus(metodospago.getEstatus());

			Utils util = new Utils();
			metodospagoExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Metodospago - update Metodospago() Method " + metodospagoExistente.toString());

			metodospagoExistente = metodospagoRepository.save(metodospagoExistente);

			response = "La Metodospago " + metodospagoExistente.getNombre() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", metodospago.toString());
		}
		return response;
	}
	
}
