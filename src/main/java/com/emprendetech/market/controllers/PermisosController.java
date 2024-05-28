package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Permisos;
import com.emprendetech.market.repositorys.PermisosRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.PermisosDto;
import com.emprendetech.market.utils.Utils;

import lombok.Data;

@Data
@Controller
public class PermisosController {

	private static final Log LOG = LogFactory.getLog(PermisosController.class);

	@Autowired
	private PermisosRepository permisosRepository;

	public String AltaPermisos(@RequestBody PermisosDto permisosDto) throws Exception {
		LOG.info("createAlta Permisos- createAlta Permisos() Method");
		LOG.debug("createAlta Permisos:: " + permisosDto.toString());

		String response = null;
		try {
			Permisos PermisosInsert = new Permisos();

			PermisosInsert.setNombre(permisosDto.getNombre());
			PermisosInsert.setDescripcion(permisosDto.getDescripcion());
			PermisosInsert.setCreadoridusuario(permisosDto.getCreadoridusuario());

			Utils util = new Utils();
			PermisosInsert.setFechacreacion(util.currentDate());
			PermisosInsert.setFechamodificacion(util.currentDate());
			
			LOG.info("Alta Permisos - Alta Permisos() Method " +PermisosInsert.toString());

			PermisosInsert = permisosRepository.save(PermisosInsert);

			response = "Felicidades Su Permisos fue registrado como = " + PermisosInsert.getNombre();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", permisosDto.toString());
		}
		return response;
	}
	
	public String actualizarPermisos(@RequestBody Permisos permisos) throws Exception {
		LOG.info("update Permisos - update Permisos() Method");
		LOG.debug("update Permisos :: " + permisos.toString());

		String response = null;

		try {
			Permisos permisosExistente = permisosRepository.findById(permisos.getIdpermisos()).orElseThrow();

			permisosExistente.setNombre(permisos.getNombre());
			permisosExistente.setDescripcion(permisos.getDescripcion());
			permisosExistente.setCreadoridusuario(permisos.getCreadoridusuario());

			Utils util = new Utils();
			permisosExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Permisos - update Permisos() Method " + permisosExistente.toString());

			permisosExistente = permisosRepository.save(permisosExistente);

			response = "El Permisos " + permisosExistente.getNombre() + " ha sido actualizado exitosamente.";
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", permisos.toString());
		}
		return response;
	}
	
}
