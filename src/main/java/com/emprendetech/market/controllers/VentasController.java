package com.emprendetech.market.controllers;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Ventas;
import com.emprendetech.market.repositorys.RolesRepository;
import com.emprendetech.market.repositorys.VentasRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.VentasDto;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;

import lombok.Data;
@Data
@Controller
public class VentasController {
	private static final Log LOG = LogFactory.getLog(VentasController.class);

	@Autowired
	private VentasRepository ventasRepository;

	public String AltaVentas(@RequestBody VentasDto ventasDto) throws Exception {
		LOG.info("createAlta Ventas- createAlta Ventas() Method");

		String response = null;
		try {
			Ventas VentasInsert = new Ventas();
			Date dateVentas = new Date(); 

			VentasInsert.setIdmetodospago(ventasDto.getIdmetodospago());
			VentasInsert.setTotal(ventasDto.getTotal());
			VentasInsert.setFechaventa(dateVentas);
			VentasInsert.setCreadoridusuario(ventasDto.getCreadoridusuario());
			VentasInsert.setTipo(ventasDto.getTipo());
			VentasInsert.setEstatus(ventasDto.getEstatus());

			Utils util = new Utils();
			VentasInsert.setFechacreacion(util.currentDate());
			VentasInsert.setFechamodificacion(util.currentDate());
			
			LOG.info("Alta Ventas - Alta Ventas() Method " + VentasInsert.toString());

			VentasInsert = ventasRepository.save(VentasInsert);

			response = Constantes.FELICIDADESALTAVENTAS + VentasInsert.getIdventa();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", ventasDto.toString());
		}
		return response;
	}
	
	public String actualizarVentas(@RequestBody Ventas ventas) throws Exception {
		LOG.info("update Ventas - update Ventas() Method");

		String response = null;

		try {
			Ventas ventasExistente = ventasRepository.findById(ventas.getIdventa()).orElseThrow();

			ventasExistente.setIdmetodospago(ventas.getIdmetodospago());
			ventasExistente.setTotal(ventas.getTotal());
			ventasExistente.setCreadoridusuario(ventas.getCreadoridusuario());
			ventasExistente.setTipo(ventas.getTipo());
			ventasExistente.setEstatus(ventas.getEstatus());
			Utils util = new Utils();
			ventasExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Ventas - update Ventas() Method " + ventasExistente.toString());

			ventasExistente = ventasRepository.save(ventasExistente);

			response = Constantes.FELICIDADESACTUALIZARVENTAS + ventasExistente.getIdventa();
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", ventas.toString());
		}
		return response;
	}
}
