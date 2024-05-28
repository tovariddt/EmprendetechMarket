package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Precio;
import com.emprendetech.market.repositorys.PrecioRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.PrecioDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class PrecioController {

	private static final Log LOG = LogFactory.getLog(PrecioController.class);

	@Autowired
	private PrecioRepository precioRepository;

	public String AltaPrecio(@RequestBody PrecioDto precioDto) throws Exception {
		LOG.info("createAlta Precio- createAlta Precio() Method");
		LOG.debug("createAlta Precio:: " + precioDto.toString());

		String response = null;
		try {
			Precio PrecioInsert = new Precio();

			PrecioInsert.setIdproductounidad(precioDto.getIdproductounidad());
			PrecioInsert.setPrecio(precioDto.getPrecio());
			PrecioInsert.setCreadoridusuario(precioDto.getCreadoridusuario());

			Utils util = new Utils();
			PrecioInsert.setFechacreacion(util.currentDate());
			PrecioInsert.setFechamodificacion(util.currentDate());
			
			LOG.info("Alta Precio - Alta Precio() Method " + PrecioInsert.toString());

			PrecioInsert = precioRepository.save(PrecioInsert);

			response = "Felicidades Su Precio fue registrado con el siguiente ID = " + PrecioInsert.getIdprecio();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", precioDto.toString());
		}
		return response;
	}
	
	public String actualizarPrecio(@RequestBody Precio precio) throws Exception {
		LOG.info("update Precio - update Precio() Method");
		LOG.debug("update Precio :: " + precio.toString());

		String response = null;

		try {
			Precio precioExistente = precioRepository.findById(precio.getIdprecio()).orElseThrow();
	
			precioExistente.setIdproductounidad(precio.getIdproductounidad());
			precioExistente.setPrecio(precio.getPrecio());
			precioExistente.setCreadoridusuario(precio.getCreadoridusuario());
			
			Utils util = new Utils();
			precioExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Precio - update Precio() Method " + precioExistente.toString());

			precioExistente = precioRepository.save(precioExistente);

			response = "El Precio " + precioExistente.getIdprecio() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", precio.toString());
		}
		return response;
	}


}
