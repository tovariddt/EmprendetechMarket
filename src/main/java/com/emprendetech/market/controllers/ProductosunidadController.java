package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Productosunidad;
import com.emprendetech.market.repositorys.ProductosunidadRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.ProductosunidadDto;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;

import lombok.Data;
@Data
@Controller
public class ProductosunidadController {

	private static final Log LOG = LogFactory.getLog(ProductosunidadController.class);

	@Autowired
	private ProductosunidadRepository productosunidadRepository;

	public String AltaProductosunidad(@RequestBody ProductosunidadDto productosunidadDto) throws Exception {
		LOG.info("createAlta Productosunidad- createAlta Productosunidad() Method");

		String response = null;
		try {
			Productosunidad ProductosunidadInsert = new Productosunidad();

			ProductosunidadInsert.setIdproducto(productosunidadDto.getIdproducto());	
			ProductosunidadInsert.setIdmedida(productosunidadDto.getIdmedida());
			ProductosunidadInsert.setCreadoridusuario(productosunidadDto.getCreadoridusuario());

			Utils util = new Utils();
			ProductosunidadInsert.setFechacreacion(util.currentDate());
			ProductosunidadInsert.setFechamodificacion(util.currentDate());
			
			LOG.info("Alta Productosunidad - Alta Precio() Method " + ProductosunidadInsert.toString());

			ProductosunidadInsert = productosunidadRepository.save(ProductosunidadInsert);

			response =Constantes.FELICIDADESALTAPRODUCTOSUNIDAD + ProductosunidadInsert.getIdproductounidad();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error",  productosunidadDto.toString());
		}
		return response;
	}
	
	public String actualizarProductosunidad(@RequestBody Productosunidad productosunidad) throws Exception {
		LOG.info("update Productosunidad - update Productosunidad() Method");

		String response = null;

		try {
			Productosunidad productosunidadExistente = productosunidadRepository.findById(productosunidad.getIdproductounidad()).orElseThrow();
	
			productosunidadExistente.setIdproducto(productosunidad.getIdproducto());	
			productosunidadExistente.setIdmedida(productosunidad.getIdmedida());
			productosunidadExistente.setCreadoridusuario(productosunidad.getCreadoridusuario());
			
			Utils util = new Utils();
			productosunidadExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Productosunidad - update Productosunidad() Method " +  productosunidadExistente.toString());

			productosunidadExistente = productosunidadRepository.save(productosunidadExistente);

			response = Constantes.FELICIDADESACTUALIZARPRODUCTOSUNIDAD + productosunidadExistente.getIdproductounidad();
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", productosunidad.toString());
		}
		return response;
	}
}
