package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Medidas;
import com.emprendetech.market.repositorys.MedidasRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.MedidasDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;
@Data
@Controller
public class MedidasController {

	private static final Log LOG = LogFactory.getLog(MedidasController.class);

	@Autowired
	private MedidasRepository medidasRepository;

	public String AltaMedidas(@RequestBody MedidasDto medidasDto) throws Exception {
		LOG.info("createAlta Medidas- createAlta Medidas() Method");
		LOG.debug("createAlta Medidas:: " + medidasDto.toString());

		String response = null;
		try {
			Medidas MedidasInsert = new Medidas();

			MedidasInsert.setNombre(medidasDto.getNombre());
			MedidasInsert.setCreadoridusuario(medidasDto.getCreadoridusuario());
			MedidasInsert.setAbreviatura(medidasDto.getAbreviatura());


			Utils util = new Utils();
			MedidasInsert.setFechacreacion(util.currentDate());
			MedidasInsert.setFechamodificacion(util.currentDate());

			LOG.info("Alta Medidas - Alta Medidas() Method " + MedidasInsert.toString());

			MedidasInsert = medidasRepository.save(MedidasInsert);

			response = "Felicidades Su Medida fue registrado como = " + MedidasInsert.getNombre();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", medidasDto.toString());
		}
		return response;
	}

	public String actualizarMedidas(@RequestBody Medidas medidas) throws Exception {
		LOG.info("update Medidas - update Medidas() Method");
		LOG.debug("update Medidas :: " + medidas.toString());

		String response = null;

		try {
			Medidas medidasExistente = medidasRepository.findById(medidas.getIdmedida()).orElseThrow();

			medidasExistente.setNombre(medidas.getNombre());
			medidasExistente.setCreadoridusuario(medidas.getCreadoridusuario());
			medidasExistente.setAbreviatura(medidas.getAbreviatura());

			Utils util = new Utils();
			medidasExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Medidas - update Medidas() Method " + medidasExistente.toString());

			medidasExistente = medidasRepository.save(medidas);

			response = "La Medida " + medidasExistente.getNombre() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", medidas.toString());
		}
		return response;
	}
	
}
