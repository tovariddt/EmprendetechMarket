package com.emprendetech.market.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.emprendetech.market.entitys.Rolpermisos;
import com.emprendetech.market.repositorys.RolpermisosRepository;
import com.emprendetech.market.response.ResponseContenidoDTO;
import com.emprendetech.market.service.requestDto.RolpermisosDto;
import com.emprendetech.market.utils.Constantes;
import com.emprendetech.market.utils.Utils;

import lombok.Data;	

@Data
@Controller
public class RolpermisosController {

	private static final Log LOG = LogFactory.getLog(RolpermisosController.class);

	@Autowired
	private RolpermisosRepository rolpermisosRepository;

	public String AltaRol_permisos(@RequestBody RolpermisosDto rolpermisosDto) throws Exception {
		LOG.info("createAlta Rolpermisos- createAlta Rolpermisos() Method");

		String response = null;
		try {
			Rolpermisos RolpermisosInsert = new Rolpermisos();

			RolpermisosInsert.setIdrol(rolpermisosDto.getIdrol());
			RolpermisosInsert.setIdpermisos(rolpermisosDto.getIdpermisos());
			RolpermisosInsert.setCreadoridusuario(rolpermisosDto.getCreadoridusuario());

			Utils util = new Utils();
			RolpermisosInsert.setFechacreacion(util.currentDate());
			RolpermisosInsert.setFechamodificacion(util.currentDate());
			
			LOG.info("Alta Rolpermisos - Alta Rolpermisos() Method " + RolpermisosInsert.toString());

			RolpermisosInsert = rolpermisosRepository.save(RolpermisosInsert);

			response = Constantes.FELICIDADESALTAROLPERMISOS + RolpermisosInsert.getIdrolpermisos();

		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", rolpermisosDto.toString());
		}
		return response;
	}
	
	public String actualizarRolpermisos(@RequestBody Rolpermisos rolpermisos) throws Exception {
		LOG.info("update Rolpermisos - update Rolpermisos() Method");

		String response = null;

		try {
			Rolpermisos rolpermisosExistente = rolpermisosRepository.findById(rolpermisos.getIdrolpermisos()).orElseThrow();
	
			rolpermisosExistente.setIdrol(rolpermisos.getIdrol());
			rolpermisosExistente.setIdpermisos(rolpermisos.getIdpermisos());
			rolpermisosExistente.setCreadoridusuario(rolpermisos.getCreadoridusuario());
			
			Utils util = new Utils();
			rolpermisosExistente.setFechamodificacion(util.currentDate());

			LOG.info("update Rolpermisos - update Rolpermisos() Method " +  rolpermisosExistente.toString());

			rolpermisosExistente = rolpermisosRepository.save(rolpermisosExistente);

			response = Constantes.FELICIDADESACTUALIZARROLPERMISOS + rolpermisosExistente.getIdrolpermisos() ;
		} catch (Exception e) {
			LOG.info("eror" + e.getStackTrace());
			final ResponseContenidoDTO responseContenido = new ResponseContenidoDTO("error", rolpermisos.toString());
		}
		return response;
	}
}
